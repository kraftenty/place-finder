package com.placefinder.placefinder.service;


import com.placefinder.placefinder.dto.PlaceDTO;
import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.entity.Toilet;
import com.placefinder.placefinder.repository.LibraryRepository;
import com.placefinder.placefinder.repository.PublicFacilityRepository;
import com.placefinder.placefinder.repository.ToiletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final LibraryRepository libraryRepository;
    private final PublicFacilityRepository publicFacilityRepository;
    private final ToiletRepository toiletRepository;

    // 모든 장소 조회
    public List<PlaceDTO> findAllPlace() {
        List<PlaceDTO> allPlaces = new ArrayList<>();

        // Library 정보 변환
        List<Library> allLibrary = libraryRepository.findAll();
        allLibrary.forEach(library ->
                allPlaces.add(new PlaceDTO(library.getId(), library.getName(), library.getAddress(), library.getLatitude(), library.getLongitude(), "library"))
        );

        // PublicFacility 정보 변환
        List<PublicFacility> allPublicFacility = publicFacilityRepository.findAll();
        allPublicFacility.forEach(publicFacility ->
                allPlaces.add(new PlaceDTO(publicFacility.getId(), publicFacility.getName(), publicFacility.getAddress(), publicFacility.getLatitude(), publicFacility.getLongitude(), "publicfacility"))
        );

        // Toilet 정보 변환
        List<Toilet> allToilet = toiletRepository.findAll();
        allToilet.forEach(toilet ->
                allPlaces.add(new PlaceDTO(toilet.getId(), toilet.getName(), toilet.getAddress(), toilet.getLatitude(), toilet.getLongitude(), "toilet"))
        );

        return allPlaces;
    }

}
