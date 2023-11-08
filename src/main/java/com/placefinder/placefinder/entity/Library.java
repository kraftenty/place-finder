package com.placefinder.placefinder.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "website", nullable = false)
    private String website;

    @Column(name = "closed_days", nullable = false)
    private String closedDays;

    @Column(name = "week_day_opening", nullable = false)
    private Date weekDayOpening;

    @Column(name = "week_day_closing", nullable = false)
    private Date weekDayClosing;

    @Column(name = "saturday_opening", nullable = false)
    private Date saturdayOpening;

    @Column(name = "saturday_closing", nullable = false)
    private Date saturdayClosing;

    @Column(name = "holiday_opening", nullable = false)
    private Date holidayOpening;

    @Column(name = "holiday_closing", nullable = false)
    private Date holidayClosing;

    @Column(name = "book_count", nullable = false)
    private Long BookCount;

    @Column(name = "book_count_rent_available", nullable = false)
    private Long BookCountRentAvailable;

    @Column(name = "days_rent_available", nullable = false)
    private Long DaysRentAvailable;
}
