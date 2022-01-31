package com.assignment.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGivenValidCity() throws Exception {
        this.mockMvc.perform(get("/weather?city=Amsterdam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.city", equalTo("Amsterdam")))
                .andExpect(jsonPath("$.country").exists())
                .andExpect(jsonPath("$.country", equalTo("NL")))
                .andExpect(jsonPath("$.temperature").exists())
                .andExpect(jsonPath("$.temperature", closeTo(273, 80)));
    }

    @Test
    void testGivenNotExistingCityWhenGetWeatherThenNotFound() throws Exception {
        this.mockMvc.perform(get("/weather?city=NotExistingCity"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertTrue(requireNonNull(result.getResolvedException()).getMessage().startsWith("404 NOT_FOUND")));
    }

    @Test
    void testGivenMissingRequiredParamWhenGetWeatherThenBadRequest() throws Exception {
        this.mockMvc.perform(get("/weather?car=Amsterdam"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGivenNotExistingResourceThenNotFound() throws Exception {
        this.mockMvc.perform(get("/xyz?city=Amsterdam"))
                .andExpect(status().isNotFound());
    }
}
