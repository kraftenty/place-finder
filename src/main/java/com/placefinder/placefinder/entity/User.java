package com.placefinder.placefinder.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

}
