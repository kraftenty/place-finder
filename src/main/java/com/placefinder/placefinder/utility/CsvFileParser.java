package com.placefinder.placefinder.utility;

import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.entity.Toilet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileParser {

    public List<Library> parseLibraryCSV(String csvFilePath) {
        List<Library> libraries = new ArrayList<>();
        try {
            Reader reader = new FileReader(csvFilePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Library library = new Library(
                        null, //ID
                        record.get(0), // 이름
                        record.get(1), // 주소
                        Double.parseDouble(record.get(2)), // 위도
                        Double.parseDouble(record.get(3)), // 경도
                        "".equals(record.get(4)) ? null : record.get(4), // 전화번호. 없으면 null
                        "".equals(record.get(5)) ? null : record.get(5), // 홈페이지. 없으면 null
                        record.get(6), // 휴관일
                        record.get(7), // 평일 운영 시작
                        record.get(8), // 평일 운영 종료
                        record.get(9), // 토요일 운영 시작
                        record.get(10), // 토요일 운영 종료
                        record.get(11), // 공휴일 운영 시작
                        record.get(12), // 공휴일 운영 종료
                        Long.parseLong(record.get(13)), //도서 수
                        Long.parseLong(record.get(14)), //대출 가능 권수
                        Long.parseLong(record.get(15)), //대출 가능 일수
                        Long.parseLong(record.get(16)) //열람 좌석 수
                );
                libraries.add(library);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraries;
    }

    public List<PublicFacility> parsePublicFacilityCSV(String csvFilePath) throws IOException {
        List<PublicFacility> publicFacilities = new ArrayList<>();
        try {
            Reader reader = new FileReader(csvFilePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                PublicFacility publicFacility = new PublicFacility(
                        null, //ID
                        record.get(0), // 이름
                        record.get(1), // 주소
                        Double.parseDouble(record.get(2)), // 위도
                        Double.parseDouble(record.get(3)), // 경도
                        "".equals(record.get(4)) ? null : record.get(4), // 전화번호. 없으면 null
                        "".equals(record.get(5)) ? null : record.get(5), // 홈페이지. 없으면 null
                        record.get(6), // 휴관일
                        record.get(7), // 평일 운영 시작
                        record.get(8), // 평일 운영 종료
                        record.get(9), // 주말 운영 시작
                        record.get(10), // 주말 운영 종료
                        record.get(11), // 이용료
                        "".equals(record.get(12)) ? null : Long.parseLong(record.get(12)), // 수용가능인원. 없으면 null
                        "".equals(record.get(13)) ? null : record.get(13), // 부대시설정보. 없으면 null
                        "".equals(record.get(14)) ? null : record.get(14) // 신청방법. 없으면 null
                );
                publicFacilities.add(publicFacility);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicFacilities;
    }

    public List<Toilet> parseToiletCSV(String csvFilePath) throws IOException {
        List<Toilet> toilets = new ArrayList<>();
        try {
            Reader reader = new FileReader(csvFilePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Toilet toilet = new Toilet(
                        null, //ID
                        record.get(0), // 이름
                        record.get(1), // 주소
                        Double.parseDouble(record.get(2)), // 위도
                        Double.parseDouble(record.get(3)), // 경도
                        record.get(4), // 개방시간
                        record.get(5), // 비상벨
                        record.get(6) // 기저귀 교환대
                );
                toilets.add(toilet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toilets;
    }


}
