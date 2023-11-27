package com.placefinder.placefinder.controller;


import com.placefinder.placefinder.service.EvaluationService;
import com.placefinder.placefinder.service.LibraryService;
import com.placefinder.placefinder.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final UserService userService;
    private final LibraryService libraryService;
    private final EvaluationService evaluationService;

    @GetMapping(value = "/placelist/library/{id}")
    public String libraryDetail(
            @PathVariable("id") Long id,
            @RequestParam String type,
            @RequestParam Integer distance,
            @RequestParam Double lat,
            @RequestParam Double lng,
            Model model,
            HttpSession session
    ) {
        // 세션이 없으면 로그인 페이지로 리다이렉트
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("library", libraryService.findOne(id));
        model.addAttribute("evaluations", evaluationService.findByPlace("library", id));
        model.addAttribute("user", userService.findOne((Long) session.getAttribute("user")));
        model.addAttribute("type", type);
        model.addAttribute("distance", distance);
        model.addAttribute("lat", lat);
        model.addAttribute("lng", lng);
        return "places/libraryDetail";
    }
}
