package com.placefinder.placefinder.utility;
import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
public class LibraryDataLoader implements ApplicationRunner {

    @Autowired
    private LibraryRepository libraryRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        CsvFileParser csvFileParser = new CsvFileParser();
        try {
            Resource resource = new ClassPathResource("csv/library.csv");
            List<Library> libraries = csvFileParser.parseLibraryCSV(resource.getFile().getAbsolutePath());
            libraryRepository.saveAll(libraries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
