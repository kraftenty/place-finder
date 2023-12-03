package com.placefinder.placefinder.utility;
import com.placefinder.placefinder.entity.Toilet;
import com.placefinder.placefinder.repository.ToiletRepository;
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
public class ToiletDataLoader implements ApplicationRunner {

    @Autowired
    private ToiletRepository toiletRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        CsvFileParser csvFileParser = new CsvFileParser();
        try {
            Resource resource = new ClassPathResource("csv/toilet.csv");
            List<Toilet> toilets = csvFileParser.parseToiletCSV(resource.getFile().getAbsolutePath());
            toiletRepository.saveAll(toilets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
