package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Toilet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToiletRepositoryTest {

    @Autowired
    ToiletRepository toiletRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testToilet() throws Exception {
        // given
        Toilet toilet = new Toilet();
        toilet.setName("toiletA");
        toilet.setAddress("서울특별시 무슨구 무슨동 무슨로 123");
        toilet.setLatitude(37.123456);
        toilet.setLongitude(127.123456);
        toilet.setOpeningHours("09:00 ~ 18:00");
        toilet.setEmergencyBell("여자화장실");
        toilet.setDiaperChange("없음");

        // when
        Long savedId = toiletRepository.save(toilet);
        Toilet findToilet = toiletRepository.findById(savedId);

        // then
        Assertions.assertThat(findToilet.getId()).isEqualTo(toilet.getId());
        Assertions.assertThat(findToilet.getName()).isEqualTo(toilet.getName());
        Assertions.assertThat(findToilet).isEqualTo(toilet);
    }

}