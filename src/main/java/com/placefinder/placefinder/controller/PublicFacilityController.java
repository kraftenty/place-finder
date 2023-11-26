package com.placefinder.placefinder.controller;


import com.placefinder.placefinder.service.PublicFacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PublicFacilityController {

    private final PublicFacilityService publicFacilityService;

    @GetMapping(value = "/placelist/publicfacility/{id}")
    public String publicFacilityDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("publicFacility", publicFacilityService.findOne(id));
        return "places/publicFacilityDetail";
    }
}
