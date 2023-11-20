package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryRepositoryTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testLibrary() throws Exception{
        // given
        Library library = new Library();
        library.setName("libraryA");
        library.setAddress("서울특별시 무슨구 무슨동 무슨로 123");
        library.setLatitude(37.123456);
        library.setLongitude(127.123456);
        library.setPhone("02-123-4567");
        library.setWebsite("www.librarya.com");
        library.setClosedDays("매주 월요일");
        library.setWeekDayOpening("09:00");
        library.setWeekDayClosing("18:00");
        library.setSaturdayOpening("10:00");
        library.setSaturdayClosing("17:00");
        library.setHolidayOpening("12:00");
        library.setHolidayClosing("16:00");
        library.setBookCount(30000L);
        library.setAvailableBookRentCount(3L);
        library.setAvailableBookRentDays(14L);
        library.setSeatCapacity(400L);

        // when
        Long savedId = libraryRepository.save(library);
        Library findLibrary = libraryRepository.findById(savedId);

        // then
        Assertions.assertThat(findLibrary.getId()).isEqualTo(library.getId());
        Assertions.assertThat(findLibrary.getName()).isEqualTo(library.getName());
        Assertions.assertThat(findLibrary).isEqualTo(library);
    }
}