package com.tave7.dobdob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoTokenDto {
    @JsonProperty("access_token")
    String accessToken;
}
