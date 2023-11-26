package com.placefinder.placefinder.controller;


import com.placefinder.placefinder.service.ToiletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ToiletController {

    private final ToiletService toiletService;

    @GetMapping(value = "/placelist/toilet/{id}")
    public String toiletDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("toilet", toiletService.findOne(id));
        return "places/toiletDetail";
    }
}
