package com.placefinder.placefinder.service;


import com.placefinder.placefinder.entity.Toilet;
import com.placefinder.placefinder.repository.ToiletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToiletService {

    private final ToiletRepository toiletRepository;

    // 화장실 전체 조회
    public List<Toilet> findAllToilet() {
        return toiletRepository.findAll();
    }

    // 화장실 단건 조회
    public Toilet findOne(Long id) {
        return toiletRepository.findById(id);
    }
}
