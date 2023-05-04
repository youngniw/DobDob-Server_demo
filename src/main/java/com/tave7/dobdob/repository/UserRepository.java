package com.tave7.dobdob.repository;

import com.tave7.dobdob.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // 이메일로 사용자 정보 조회
    Optional<Users> findByEmail(String email);
}
