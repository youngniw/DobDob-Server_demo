package com.tave7.dobdob.controller;

import com.tave7.dobdob.dto.KakaoTokenDto;
import com.tave7.dobdob.dto.LoginDto;
import com.tave7.dobdob.dto.UserDto;
import com.tave7.dobdob.exception.exception.UnAuthorizedException;
import com.tave7.dobdob.security.jwt.TokenProvider;
import com.tave7.dobdob.service.AuthService;
import com.tave7.dobdob.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    // 카카오 로그인
    @PostMapping(value = "/kakao")
    public ResponseEntity<LoginDto> kakoLogin(@RequestHeader(required = false, value = "authorization") String jwt,
                                              @RequestBody(required = false) KakaoTokenDto token) {
        // 카카오 계정의 access token 으로 로그인
        if (jwt == null) {
            String email = authService.kakaoLogin(token);

            Optional<UserDto> user = userService.findByEmail(email);
            // 로그인 경험 있음
            if (user.isPresent()) {
                String newJwt = tokenProvider.generateToken(user.get().getId(), email);   // 토큰 생성

                LoginDto loginDto = LoginDto.builder()
                        .jwt(newJwt)
                        .user(user.get())
                        .build();

                return ResponseEntity.ok(loginDto);
            }
            // 로그인 경험 없음(DB에 사용자 추가)
            else {
                UserDto newUser = userService.kakaoSignUp(email);   // 사용자 추가

                String newJwt = tokenProvider.generateToken(newUser.getId(), email);  // 토큰 생성

                LoginDto loginDto = LoginDto.builder()
                        .jwt(newJwt)
                        .user(newUser)
                        .build();

                return new ResponseEntity<>(loginDto, HttpStatus.CREATED);
            }
        }
        // 서버 자체 JWT 토큰으로 자동 로그인
        else {
            // 서비스에서 생성한 jwt 토큰으로 사용자 로그인
            if (!tokenProvider.validateToken(jwt))
                throw UnAuthorizedException.INVALID_JWT_TOKEN;

            Claims claims = tokenProvider.getClaims(jwt);
            Long id = Long.valueOf(claims.get("id").toString());
            String email = claims.get("email").toString();

            Optional<UserDto> user = userService.findByIdAndEmail(id, email);
            // JWT가 올바른 경우
            if (user.isPresent()) {
                LoginDto loginDto = LoginDto.builder()
                        .user(user.get())
                        .build();

                return ResponseEntity.ok(loginDto);
            }
            else {
                // 옳지 않은 jwt 이므로 예외 처리
                throw UnAuthorizedException.INVALID_JWT_TOKEN;
            }
        }
    }

    // TODO: 추가로 토큰 관련 수정 요망!
}
