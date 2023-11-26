package com.placefinder.placefinder.service;

import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.repository.PublicFacilityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PublicFacilityServiceTest {
    @Autowired PublicFacilityService publicFacilityService;
    @Autowired PublicFacilityRepository publicFacilityRepository;

    @Test
    public void 공공시설_단건_조회() throws Exception {
        //given
        PublicFacility publicFacility = new PublicFacility (
            null,
            "publicFacilityA",
            "addressA",
            1.0,
            1.0,
            "phoneA",
            "websiteA",
            "closedDaysA",
            "weekDayOpeningA",
            "weekDayClosingA",
            "holidayOpeningA",
            "holidayClosingA",
            "feeA",
            1L,
            "amenitiesA",
            "howToApplyA"
        );
        Long savedId = publicFacilityRepository.save(publicFacility);
        //when
        PublicFacility findPublicFacility = publicFacilityService.findOne(savedId);
        //then
        assertEquals(savedId, findPublicFacility.getId());
    }
}