package com.placefinder.placefinder.controller;


import com.placefinder.placefinder.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping(value = "/placelist/library/{id}")
    public String libraryDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("library", libraryService.findOne(id));
        return "places/libraryDetail";
    }
}
