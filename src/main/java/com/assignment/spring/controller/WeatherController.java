package com.assignment.spring.controller;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.WeatherDto;
import com.assignment.spring.service.WeatherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WeatherController {

    @NonNull
    private final WeatherService weatherService;

    @NonNull
    private final WeatherMapper weatherMapper;

    @GetMapping("/weather")
    public WeatherDto weather(@RequestParam String city) {
        log.info("Weather endpoint called for city: {}", city);
        WeatherEntity entity = weatherService.getWeatherByCityName(city);
        return weatherMapper.entityToDto(entity);
    }

}
