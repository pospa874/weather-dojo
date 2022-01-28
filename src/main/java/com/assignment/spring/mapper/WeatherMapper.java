package com.assignment.spring.mapper;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.WeatherDto;
import com.assignment.spring.model.openweathermapapi.WeatherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "name", target = "city")
    @Mapping(source = "sys.country", target = "country")
    @Mapping(source = "main.temp", target = "temperature")
    WeatherEntity responseToEntity(WeatherResponse response);

    WeatherDto entityToDto(WeatherEntity entity);
}
