package com.tave7.dobdob.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserDto {
    private Long id;

    private LocationDto Location;     // 이전 서버 버전에서 설정한 이름    // TODO: 리팩토링 요망 (카멜케이스로!)

    private String nickName;

    private String profileUrl;      // 프로필 이미지 링크
}
