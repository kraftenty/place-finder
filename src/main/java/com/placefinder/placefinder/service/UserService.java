package com.placefinder.placefinder.service;


import com.placefinder.placefinder.entity.User;
import com.placefinder.placefinder.exception.IncorrectPasswordException;
import com.placefinder.placefinder.exception.UserNotFoundException;
import com.placefinder.placefinder.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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

    // 회원 가입 시 중복 회원 검증
    private void validateDuplicateUser(User user) {
        List<User> findMembers = userRepository.findByName(user.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 로그인
    public User login(User user) {
        List<User> matchingUsers = userRepository.findByName(user.getName());
        if (matchingUsers.isEmpty()) {
            throw new UserNotFoundException("존재하지 않는 회원입니다.");
        }

        User foundUser = matchingUsers.get(0);
        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return foundUser;
    }

    // 회원 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String password) {
        User user = userRepository.findOne(id);
        user.updatePassword(password);
    }


    // 회원 탈퇴
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    // 회원 전체 조회
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    // 회원 단건 조회
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
