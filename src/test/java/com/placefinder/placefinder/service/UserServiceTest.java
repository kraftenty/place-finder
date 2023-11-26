package com.placefinder.placefinder.service;

import com.placefinder.placefinder.entity.User;
import com.placefinder.placefinder.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        User user = new User(null, "userA", "1111");
        //when
        Long savedId = userService.join(user);
        //then
        assertEquals(savedId, userRepository.findOne(savedId).getId());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = new User(null, "userA", "1111");
        User user2 = new User(null, "userA", "1111");

        //when
        userService.join(user1);
        try {
            userService.join(user2); // 예외가 발생해야 한다.
        } catch (IllegalStateException e) {
            return;

        }

        //then
        fail("예외가 발생해야 한다.");
    }

}