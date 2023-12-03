package com.placefinder.placefinder.controller;

import com.placefinder.placefinder.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping(value = "/placelist/{placeType}/{placeId}/save-evaluation")
    public String saveEvaluation(
            @RequestParam String username,
            @PathVariable String placeType,
            @PathVariable Long placeId,
            @RequestParam String comment,
            @RequestParam String type,
            @RequestParam Integer distance,
            @RequestParam Double lat,
            @RequestParam Double lng
    ) {
        log.info("username: {}, placeType: {}, placeId: {}, comment: {}", username, placeType, placeId, comment);
        log.info("type: {}, distance: {}, lat: {}, lng: {}", type, distance, lat, lng);
        evaluationService.save(
                username,
                placeType,
                placeId,
                comment
        );
        return String.format("redirect:/placelist/%s/%d?type=%s&distance=%d&lat=%f&lng=%f",
                placeType, placeId, type, distance, lat, lng);
    }

    @PostMapping(value = "/evaluation/delete-evaluation")
    public String deleteEvaluation(
            @RequestParam Long evaluationId,
            @RequestParam String placeType,
            @RequestParam Long placeId,
            @RequestParam String type,
            @RequestParam Integer distance,
            @RequestParam Double lat,
            @RequestParam Double lng
    ) {
        evaluationService.delete(evaluationId);
        return String.format("redirect:/placelist/%s/%d?type=%s&distance=%d&lat=%f&lng=%f",
                placeType, placeId, type, distance, lat, lng);
    }

}
