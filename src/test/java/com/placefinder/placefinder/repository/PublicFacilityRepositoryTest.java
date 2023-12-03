package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.PublicFacility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class PublicFacilityRepositoryTest {

    @Autowired
    PublicFacilityRepository publicFacilityRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testPublicFacility() throws Exception{
        //given
        PublicFacility publicFacility = new PublicFacility(
                null,
                "publicFacilityA",
                "서울특별시 무슨구 무슨동 무슨로 123",
                37.123456,
                127.123456,
                "02-123-4567",
                "www.publicfacility.com",
                "연중무휴",
                "09:00",
                "18:00",
                "10:00",
                "17:00",
                "무료",
                400L,
                null,
                "온라인 신청"
        );

        // when
        Long savedId = publicFacilityRepository.save(publicFacility);
        PublicFacility findPublicFacility = publicFacilityRepository.findById(savedId);

        // then
        Assertions.assertThat(findPublicFacility.getId()).isEqualTo(publicFacility.getId());
        Assertions.assertThat(findPublicFacility.getName()).isEqualTo(publicFacility.getName());
        Assertions.assertThat(findPublicFacility).isEqualTo(publicFacility);

    }

}