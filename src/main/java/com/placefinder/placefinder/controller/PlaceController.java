package com.placefinder.placefinder.controller;


import com.placefinder.placefinder.service.LibraryService;
import com.placefinder.placefinder.service.PlaceService;
import com.placefinder.placefinder.service.PublicFacilityService;
import com.placefinder.placefinder.service.ToiletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping(value = "/placelist")
    public String place(Model model) {
        model.addAttribute("places", placeService.findAllPlace());
        return "places/placelist";
    }



}
