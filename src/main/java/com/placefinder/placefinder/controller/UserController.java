package com.placefinder.placefinder.controller;

import com.placefinder.placefinder.dto.UserDTO;
import com.placefinder.placefinder.entity.User;
import com.placefinder.placefinder.exception.IncorrectPasswordException;
import com.placefinder.placefinder.exception.UserNotFoundException;
import com.placefinder.placefinder.service.EvaluationService;
import com.placefinder.placefinder.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EvaluationService evaluationService;

    @GetMapping(value = "/user/new")
    public String createForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "users/createUserForm";
    }

    @PostMapping(value = "/user/new")
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

    @GetMapping(value = "/user/login")
    public String loginForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "users/loginForm";
    }

    @PostMapping(value = "/user/login")
    public String login(@Valid UserDTO dto, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "users/loginForm";
        }
        try {
            User user = new User(dto);
            user = userService.login(user);
            session.setAttribute("user", user.getId());
            return "redirect:/";
        } catch (UserNotFoundException e) {
            log.error("Error: {}", e.getMessage());
            result.rejectValue("name", "user.notfound", "존재하지 않는 사용자입니다.");
            return "users/loginForm";
        } catch (IncorrectPasswordException e) {
            log.error("Error: {}", e.getMessage());
            result.rejectValue("password", "password.incorrect", "비밀번호가 틀렸습니다.");
            return "users/loginForm";
        }

    }

    @GetMapping(value = "/user/logout")
    public String logout(HttpSession session) {
            session.removeAttribute("user");
            return "redirect:/";
    }

    @GetMapping(value = "/user/update")
    public String updateForm(HttpSession session, Model model) {
        // 세션에 유저가 존재하면 업데이트 폼으로 보내고, 존재하지 않으면 로그인 폼으로 보낸다.
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        } else {
            User user = userService.findOne((Long) session.getAttribute("user"));
            model.addAttribute("user", user);
            return "users/updateUserForm";
        }
    }

    @PostMapping(value = "/user/update")
    public String update(@RequestParam String new_password, HttpSession session) {
        // 세션이 없으면 로그인 페이지로 리다이렉트
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        userService.updatePassword((Long) session.getAttribute("user"), new_password);
        return "redirect:/";
    }

    @GetMapping(value = "/user/delete")
    public String delete(HttpSession session) {
        // 세션이 없으면 로그인 페이지로 리다이렉트
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        evaluationService.deleteAllByUser(userService.findOne((Long) session.getAttribute("user")).getName()); // 유저가 작성한 모든 평가 삭제
        userService.delete((Long) session.getAttribute("user"));
        session.removeAttribute("user"); // 세션에서 유저 삭제
        return "redirect:/";
    }

}
