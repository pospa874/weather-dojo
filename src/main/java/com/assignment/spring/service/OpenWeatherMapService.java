package com.assignment.spring.service;

import com.assignment.spring.model.openweathermapapi.WeatherResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.assignment.spring.common.Constants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherMapService {

    @Value("${openweathermap.api.base-url}")
    private String baseUrl;

    @Value("${openweathermap.appid}")
    private String appId;

    @NonNull
    private final RestTemplate restTemplate;

    public WeatherResponse getWeatherByCityName(String city) {
        log.info("OpenWeatherMapService call with {} {}", appId, baseUrl);
        return this.getWeatherByCityName(city, this.baseUrl, this.appId);
    }


    private WeatherResponse getWeatherByCityName(String city, @NonNull String url, @NonNull String appId) {
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(buildUrl(url, city, appId), WeatherResponse.class);
        log.info("status code {}", response.getStatusCode());
        return response.getBody();
    }

    private static URI buildUrl(String url, String city, String appId) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(URI_PATH_WEATHER)
                .queryParam(QUERY_PARAM_Q, city)
                .queryParam(QUERY_PARAM_APPID, appId)
                .build()
                .toUri();
    }
}
