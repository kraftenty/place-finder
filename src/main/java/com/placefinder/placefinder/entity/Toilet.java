package com.placefinder.placefinder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
public class Toilet {

    public Toilet() { }

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

    @Column(name= "opening_hours", nullable = false)
    private String openingHours; // 개방시간

    @Column(name = "emergency_bell", nullable = false)
    private String emergencyBell; // 비상벨

    @Column(name = "diaper_change", nullable = false)
    private String diaperChange; // 기저귀 교환대

}
