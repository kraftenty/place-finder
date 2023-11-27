package com.placefinder.placefinder.service;


import com.placefinder.placefinder.dto.PlaceDTO;
import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.entity.Toilet;
import com.placefinder.placefinder.repository.LibraryRepository;
import com.placefinder.placefinder.repository.PublicFacilityRepository;
import com.placefinder.placefinder.repository.ToiletRepository;
import com.placefinder.placefinder.utility.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
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
                allPlaces.add(new PlaceDTO(library.getId(), library.getName(), library.getAddress(), library.getLatitude(), library.getLongitude(), "library", null))
        );

        // PublicFacility 정보 변환
        List<PublicFacility> allPublicFacility = publicFacilityRepository.findAll();
        allPublicFacility.forEach(publicFacility ->
                allPlaces.add(new PlaceDTO(publicFacility.getId(), publicFacility.getName(), publicFacility.getAddress(), publicFacility.getLatitude(), publicFacility.getLongitude(), "publicfacility", null))
        );

        // Toilet 정보 변환
        List<Toilet> allToilet = toiletRepository.findAll();
        allToilet.forEach(toilet ->
                allPlaces.add(new PlaceDTO(toilet.getId(), toilet.getName(), toilet.getAddress(), toilet.getLatitude(), toilet.getLongitude(), "toilet", null))
        );

        return allPlaces;
    }

    // 위치 기반 선택적 장소 조회
    public List<PlaceDTO> findPlaceSelectively(String type, Integer distance, Double lat, Double lng) {
        List<PlaceDTO> places = new ArrayList<>();
        if (type.equals("library")) {
            log.info("library selected");
            List<Library> libraries = libraryRepository.findLibrariesWithinDistance(lat, lng, distance);
            libraries.forEach(library ->
                    places.add(new PlaceDTO(
                            library.getId(),
                            library.getName(),
                            library.getAddress(),
                            library.getLatitude(),
                            library.getLongitude(),
                            "library",
                            DistanceCalculator.calculateDistanceInMeters(lat, lng, library.getLatitude(), library.getLongitude())
                            )
                    )
            );
        }
        else if (type.equals("publicfacility")) {
            List<PublicFacility> publicFacilities = publicFacilityRepository.findPublicFacilitiesWithinDistance(lat, lng, distance);
            publicFacilities.forEach(publicFacility ->
                    places.add(new PlaceDTO(
                            publicFacility.getId(),
                            publicFacility.getName(),
                            publicFacility.getAddress(),
                            publicFacility.getLatitude(),
                            publicFacility.getLongitude(),
                            "publicfacility",
                            DistanceCalculator.calculateDistanceInMeters(lat, lng, publicFacility.getLatitude(), publicFacility.getLongitude())
                            )
                    )
            );
        }
        else {
            List<Toilet> toilets = toiletRepository.findToiletsWithinDistance(lat, lng, distance);
            toilets.forEach(toilet ->
                    places.add(new PlaceDTO(
                            toilet.getId(),
                            toilet.getName(),
                            toilet.getAddress(),
                            toilet.getLatitude(),
                            toilet.getLongitude(),
                            "toilet",
                            DistanceCalculator.calculateDistanceInMeters(lat, lng, toilet.getLatitude(), toilet.getLongitude())
                            )
                    )
            );
        }


        return places;
    }
}
