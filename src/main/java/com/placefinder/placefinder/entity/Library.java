package com.placefinder.placefinder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libraries")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "address", nullable = false)
    private String address; // 주소

    @Column(name = "latitude", nullable = false)
    private Double latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; // 경도

    @Column(name = "phone", nullable = true)
    private String phone; // 전화번호. 없으면 null

    @Column(name = "website", nullable = true)
    private String website; // 홈페이지. 없으면 null

    @Column(name = "closed_days", nullable = false)
    private String closedDays; // 휴관일

    @Column(name = "week_day_opening", nullable = false)
    private String weekDayOpening; // 평일 운영 시작

    @Column(name = "week_day_closing", nullable = false)
    private String weekDayClosing; // 평일 운영 종료

    @Column(name = "saturday_opening", nullable = false)
    private String saturdayOpening; // 토요일 운영 시작

    @Column(name = "saturday_closing", nullable = false)
    private String saturdayClosing; // 토요일 운영 종료

    @Column(name = "holiday_opening", nullable = false)
    private String holidayOpening; // 공휴일 운영 시작

    @Column(name = "holiday_closing", nullable = false)
    private String holidayClosing; // 공유일 운영 종료

    @Column(name = "book_count", nullable = false)
    private Long bookCount; // 도서 수

    @Column(name = "available_book_rent_count", nullable = false)
    private Long availableBookRentCount; // 대출 가능 권수

    @Column(name = "available_book_rent_days", nullable = false)
    private Long availableBookRentDays; // 대출 가능 일수

    @Column(name = "seat_capacity", nullable = false)
    private Long seatCapacity; // 열람 좌석 수

}
