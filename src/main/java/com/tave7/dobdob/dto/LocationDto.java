package com.tave7.dobdob.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class LocationDto {
    private String dong;

    private String detail;

    private Float locationX;

    private Float locationY;
}
