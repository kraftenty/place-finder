package com.placefinder.placefinder.service;


import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.repository.PublicFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicFacilityService {

    private final PublicFacilityRepository publicFacilityRepository;

    // 공공시설 전체 조회
    public List<PublicFacility> findAllPublicFacility() {
        return publicFacilityRepository.findAll();
    }

    // 공공시설 단건 조회
    public PublicFacility findOne(Long id) {
        return publicFacilityRepository.findById(id);
    }
}
