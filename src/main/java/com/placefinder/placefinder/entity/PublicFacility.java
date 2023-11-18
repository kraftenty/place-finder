package com.placefinder.placefinder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
public class PublicFacility {

    public PublicFacility() { }

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
    private String phone; // 전화번호

    @Column(name = "website", nullable = true)
    private String website; // 홈페이지

    @Column(name = "closed_days", nullable = false)
    private String closedDays; // 휴관일

    @Column(name = "week_day_opening", nullable = false)
    private String weekDayOpening; // 평일 운영 시작

    @Column(name = "week_day_closing", nullable = false)
    private String weekDayClosing; // 평일 운영 종료

    @Column(name = "weekend_opening", nullable = false)
    private String weekendOpening; // 주말 운영 시작

    @Column(name = "weekend_closing", nullable = false)
    private String weekendClosing; // 주말 운영 종료

    @Column(name = "fee", nullable = false)
    private String fee; // 이용료

    @Column(name = "capacity", nullable = true)
    private Long capacity; // 수용가능인원. 없으면 null

    @Column(name = "amenities", nullable = true)
    private String amenities; // 부대시설정보. 없으면 null

    @Column(name = "how_to_apply", nullable = true)
    private String howToApply; // 신청방법. 없으면 null

}
