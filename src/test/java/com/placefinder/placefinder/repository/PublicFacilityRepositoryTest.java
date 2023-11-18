package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.PublicFacility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublicFacilityRepositoryTest {

    @Autowired
    PublicFacilityRepository publicFacilityRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testPublicFacility() throws Exception{
        //given
        PublicFacility publicFacility = new PublicFacility();
        publicFacility.setName("publicFacilityA");
        publicFacility.setAddress("서울특별시 무슨구 무슨동 무슨로 123");
        publicFacility.setLatitude(37.123456);
        publicFacility.setLongitude(127.123456);
        publicFacility.setPhone("02-123-4567");
        publicFacility.setWebsite("www.publicfacility.com");
        publicFacility.setClosedDays("연중무휴");
        publicFacility.setWeekDayOpening("09:00");
        publicFacility.setWeekDayClosing("18:00");
        publicFacility.setWeekendOpening("10:00");
        publicFacility.setWeekendClosing("17:00");
        publicFacility.setFee("무료");
        publicFacility.setCapacity(400L);
        publicFacility.setAmenities(null);
        publicFacility.setHowToApply("온라인 신청");

        // when
        Long savedId = publicFacilityRepository.save(publicFacility);
        PublicFacility findPublicFacility = publicFacilityRepository.findById(savedId);

        // then
        Assertions.assertThat(findPublicFacility.getId()).isEqualTo(publicFacility.getId());
        Assertions.assertThat(findPublicFacility.getName()).isEqualTo(publicFacility.getName());
        Assertions.assertThat(findPublicFacility).isEqualTo(publicFacility);

    }

}