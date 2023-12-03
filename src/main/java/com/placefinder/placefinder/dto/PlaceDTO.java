package com.placefinder.placefinder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlaceDTO {

    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String type;
    private String distance;

}
