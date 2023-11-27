package com.placefinder.placefinder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.placefinder.placefinder.dto.PlaceDTO;
import com.placefinder.placefinder.service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/place")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping(value = "/search")
    public String search() {
        return "places/search";
    }


    @GetMapping(value = "/search/result")
    public String searchResult(
            @RequestParam String type,
            @RequestParam Integer distance,
            @RequestParam Double lat,
            @RequestParam Double lng,
            Model model,
            HttpSession session) {
        // 세션이 없으면 로그인 페이지로 리다이렉트
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("type: {}, distance: {}, lat: {}, lng: {}", type, distance, lat, lng);
        List<PlaceDTO> places = placeService.findPlaceSelectively(type, distance, lat, lng);
        try {
            String placesjson = objectMapper.writeValueAsString(places);
            model.addAttribute("placesjson", placesjson);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: {}", e.getMessage());
        }
        model.addAttribute("type", type);
        model.addAttribute("distance", distance);
        model.addAttribute("lat", lat);
        model.addAttribute("lng", lng);
        model.addAttribute("places", places);
        return "places/searchResult";
    }

    /**
     * 장소 전체 조회. 디버깅용.
     */
//    @GetMapping(value = "/list")
//    public String list(Model model) {
//        model.addAttribute("places", placeService.findAllPlace());
//        return "places/list";
//    }


}
