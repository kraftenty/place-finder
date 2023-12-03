package com.placefinder.placefinder.service;

import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class LibraryServiceTest {
    @Autowired LibraryService libraryService;
    @Autowired LibraryRepository libraryRepository;

    @Test
    public void 도서관_단건_조회() throws Exception {
        //given
        Library library = new Library(
                null,
                "libraryA",
                "addressA",
                1.0,
                1.0,
                "phoneA",
                "websiteA",
                "closedDaysA",
                "weekDayOpeningA",
                "weekDayClosingA",
                "saturdayOpeningA",
                "saturdayClosingA",
                "holidayOpeningA",
                "holidayClosingA",
                1L,
                1L,
                1L,
                1L
        );
        Long savedId = libraryRepository.save(library);
        //when
        Library findLibrary = libraryService.findOne(savedId);
        //then
        assertEquals(savedId, findLibrary.getId());
    }
}