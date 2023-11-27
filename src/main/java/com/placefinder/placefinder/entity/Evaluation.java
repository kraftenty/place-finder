package com.placefinder.placefinder.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 평가 ID

    @Column(name = "username", nullable = false)
    private String username; // 유저 이름

    @Column(name = "place_type", nullable = false)
    private String placeType; // 장소 타입

    @Column(name = "place_id", nullable = false)
    private Long placeId; // 장소 ID

    @Column(name = "comment", nullable = false)
    private String comment; // 평가 코멘트
}
