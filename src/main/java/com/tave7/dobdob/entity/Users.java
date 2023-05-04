package com.tave7.dobdob.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Locations location;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String socialType;

    @Column
    private String nickName;

    @Column
    private String profileUrl;      // 프로필 이미지 링크
}
