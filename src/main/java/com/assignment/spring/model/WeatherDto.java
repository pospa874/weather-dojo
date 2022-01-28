package com.assignment.spring.model;

import lombok.Data;

@Data
public class WeatherDto {

    private String city;

    private String country;

    private Double temperature;

}
