package com.tave7.dobdob.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tave7.dobdob.dto.KakaoTokenDto;
import com.tave7.dobdob.exception.exception.BadRequestException;
import com.tave7.dobdob.exception.exception.ForbiddenException;
import com.tave7.dobdob.exception.exception.InternalServerException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class AuthService {
    // 카카오 로그인
    public String kakaoLogin(KakaoTokenDto token) {
        // 카카오 로그인 진행 (by 카카오 토큰)
        RestTemplate template = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(token.getAccessToken()));
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        // 카카오와의 통신
        ResponseEntity<String> response;
        try {
            response = template.postForEntity(
                    "https://kapi.kakao.com/v2/user/me",
                    request,
                    String.class);
        }
        catch (HttpClientErrorException e) {
            // HTTP 요청 오류 - 토큰이 잘못되어 정보 조회 안됨
            throw BadRequestException.NOT_VALID_SOCIAL_LOGIN_TOKEN;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String email;
        try {
            JsonNode userInfo = objectMapper.readTree(response.getBody()).get("kakao_account");

            if (!userInfo.get("email_needs_agreement").asBoolean()) {
                email = userInfo.get("email").asText();
            }
            else {
                // 사용자가 이메일 제공을 동의하지 않았기에 동의 받아야 함 (403)
                throw ForbiddenException.NOT_AGREE_SOCIAL_LOGIN;
            }
        } catch (JsonProcessingException e) {
            throw InternalServerException.ALL_INTERNAL_EXCEPTION;
        }

        return email;
    }
}
