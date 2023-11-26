package com.placefinder.placefinder.service;


import com.placefinder.placefinder.entity.User;
import com.placefinder.placefinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user); // 중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findMembers = userRepository.findByName(user.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 회원 단건 조회
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
