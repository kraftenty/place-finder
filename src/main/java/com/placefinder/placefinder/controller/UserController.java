package com.placefinder.placefinder.controller;

import com.placefinder.placefinder.dto.UserDTO;
import com.placefinder.placefinder.entity.User;
import com.placefinder.placefinder.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/new")
    public String createForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(@Valid UserDTO dto, BindingResult result) {
        /**
         * BindingResult는 검증 오류가 발생하면 해당 결과를 보관한다.
         * 이걸 활용하여 폼으로 에러 메세지와 함께 다시 돌아가게 할 수 있다.
         */
        if (result.hasErrors()) {
            return "users/createUserForm";
        }

        try {
            User user = new User(dto);
            userService.join(user);
        } catch (IllegalStateException e) {
            log.error("Error: {}", e.getMessage());
            result.rejectValue("name", "duplicate", "이미 존재하는 이름입니다.");
            return "users/createUserForm";
        }

        return "redirect:/"; //리다이렉트로 홈으로 보내버림
    }

}
