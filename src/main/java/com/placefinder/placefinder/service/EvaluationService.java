package com.placefinder.placefinder.service;

import com.placefinder.placefinder.entity.Evaluation;
import com.placefinder.placefinder.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public List<Evaluation> findByPlace(String placeType, Long placeId) {
        return evaluationRepository.findByPlace(placeType, placeId);
    }

    @Transactional
    public void save(String username, String placeType, Long placeId, String comment) {
        Evaluation evaluation = new Evaluation(
            null,
            username,
            placeType,
            placeId,
            comment
        );
        evaluationRepository.save(evaluation);
    }

    @Transactional
    public void delete(Long id) {
        evaluationRepository.delete(id);
    }

    @Transactional
    public void deleteAllByUser(String username) {
        evaluationRepository.deleteAllByUser(username);
    }
}
