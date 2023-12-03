package com.placefinder.placefinder.service;

import com.placefinder.placefinder.entity.Toilet;
import com.placefinder.placefinder.repository.ToiletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToiletServiceTest {
    @Autowired ToiletService toiletService;
    @Autowired ToiletRepository toiletRepository;

    @Test
    public void 화장실_단건_조회() throws Exception {
        //given
        Toilet toilet = new Toilet(
            null,
            "toiletA",
            "addressA",
            1.0,
            1.0,
            "openingHoursA",
            "emergencyBellA",
            "diaperChangeA"
        );
        Long savedId = toiletRepository.save(toilet);
        //when
        Toilet findToilet = toiletService.findOne(savedId);
        //then
        assertEquals(savedId, findToilet.getId());
    }
}