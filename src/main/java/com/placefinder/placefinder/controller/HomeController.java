package com.placefinder.placefinder.controller;

import com.placefinder.placefinder.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;

    @RequestMapping(value = "/")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("username", userService.findOne((Long) session.getAttribute("user")).getName());
            return "home";
        }
    }
}
