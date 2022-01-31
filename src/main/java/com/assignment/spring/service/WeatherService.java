package com.assignment.spring.service;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.openweathermapapi.WeatherResponse;
import com.assignment.spring.repository.WeatherRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    @NonNull
    private final WeatherRepository weatherRepository;

    @NonNull
    private final WeatherMapper weatherMapper;

    @NonNull
    private final OpenWeatherMapService openWeatherMapService;

    @Transactional
    public WeatherEntity getWeatherByCityName(String city) {
        WeatherResponse weatherByCityName = openWeatherMapService.getWeatherByCityName(city);
        WeatherEntity entity = weatherMapper.responseToEntity(weatherByCityName);
        log.debug("Storing data to DB");
        return weatherRepository.save(entity);
    }
}
