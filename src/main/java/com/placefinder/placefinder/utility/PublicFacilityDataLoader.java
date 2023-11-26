package com.placefinder.placefinder.utility;
import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.repository.PublicFacilityRepository;
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
public class PublicFacilityDataLoader implements ApplicationRunner {

    @Autowired
    private PublicFacilityRepository publicFacilityRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        CsvFileParser csvFileParser = new CsvFileParser();
        try {
            Resource resource = new ClassPathResource("csv/public_facility.csv");
            List<PublicFacility> publicFacilities = csvFileParser.parsePublicFacilityCSV(resource.getFile().getAbsolutePath());
            publicFacilityRepository.saveAll(publicFacilities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
