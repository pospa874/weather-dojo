package com.assignment.spring.service;

import com.assignment.spring.model.openweathermapapi.WeatherResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
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

    public WeatherResponse getWeatherByCityName(@NonNull String city) {
        return this.getWeatherByCityName(this.baseUrl, city, this.appId);
    }


    private WeatherResponse getWeatherByCityName(@NonNull String url, String city, @NonNull String appId) {
        try {
            log.info("Calling OpenWeatherMap API for city {}", city);
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(buildURI(url, city, appId), WeatherResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Call to OpenWeatherMap API failed", e);
            throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString(), e);
        } catch (RestClientException e) {
            log.error("Call to OpenWeatherMap API failed", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get data from the provider", e);
        }

    }

    private static URI buildURI(String url, String city, String appId) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(URI_PATH_WEATHER)
                .queryParam(QUERY_PARAM_Q, city)
                .queryParam(QUERY_PARAM_APPID, appId)
                .build()
                .toUri();
    }
}
