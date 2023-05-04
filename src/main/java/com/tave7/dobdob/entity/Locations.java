package com.tave7.dobdob.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable = false)
    private String si;          // 시도

    @Column(nullable = false)
    private String gu;          // 시군구

    @Column(nullable = false)
    private String dong;        // 읍면동

    @Column(name = "location_x", nullable = false)
    private Float locationX;

    @Column(name = "location_y", nullable = false)
    private Float locationY;

    @Column(nullable = false)
    private String detail;      // 상세 주소
}
