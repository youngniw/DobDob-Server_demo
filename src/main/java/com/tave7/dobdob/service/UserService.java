package com.tave7.dobdob.service;

import com.tave7.dobdob.dto.UserDto;
import com.tave7.dobdob.entity.Users;
import com.tave7.dobdob.mapper.UserMapper;
import com.tave7.dobdob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // 동일한 id를 갖는 해당 사용자 정보 반환
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    // 동일한 이메일을 갖는 해당 사용자 정보 반환
    @Transactional(readOnly = true)
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }

    // 동일한 번호와 이메일을 갖는 사용자 정보 반환
    @Transactional(readOnly = true)
    public Optional<UserDto> findByIdAndEmail(Long id, String email) {
        return userRepository.findByUserIdAndEmail(id, email)
                .map(userMapper::toDto);
    }

    // 회원가입
    @Transactional
    public UserDto kakaoSignUp(String email) {
        Users user = Users.builder()
                .email(email)
                .socialType("kakao")
                .build();
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }
}
