package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class LibraryRepositoryTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testLibrary() throws Exception {
        // given
        Library library = new Library(
                null,
                "libraryA",
                "서울특별시 무슨구 무슨동 무슨로 123",
                37.123456,
                127.123456,
                "02-123-4567",
                "www.librarya.com",
                "매주 월요일",
                "09:00",
                "18:00",
                "10:00",
                "17:00",
                "12:00",
                "16:00",
                30000L,
                3L,
                14L,
                400L
        );

        // when
        Long savedId = libraryRepository.save(library);
        Library findLibrary = libraryRepository.findById(savedId);

        // then
        Assertions.assertThat(findLibrary.getId()).isEqualTo(library.getId());
        Assertions.assertThat(findLibrary.getName()).isEqualTo(library.getName());
        Assertions.assertThat(findLibrary).isEqualTo(library);
    }
}