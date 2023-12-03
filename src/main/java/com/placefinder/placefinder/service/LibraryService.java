package com.placefinder.placefinder.service;


import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.repository.LibraryRepository;
import com.placefinder.placefinder.utility.CsvFileParser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    // 도서관 전체 조회
    public List<Library> findAllLibrary() {
        return libraryRepository.findAll();
    }

    // 도서관 단건 조회
    public Library findOne(Long id) {
        return libraryRepository.findById(id);
    }

}
