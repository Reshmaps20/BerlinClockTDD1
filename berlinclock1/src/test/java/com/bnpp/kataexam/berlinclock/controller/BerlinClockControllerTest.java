package com.bnpp.kataexam.berlinclock.controller;

import com.bnpp.kataexam.berlinclock.model.BerlinClockRequest;
import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.service.BerlinClockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bnpp.kataexam.berlinclock.constants.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BerlinClockControllerTest {

    @Autowired
    MockMvc mockMvc;
    private TimeInput timeInput;
    private DetailedBerlinTime detailedBerlinTime;

    private BerlinClockRequest request;

    @BeforeEach
    public void setup() {
        timeInput = new TimeInput(FOURTEEN_HOUR, TWENTYFOUR_MINUTE, FIVE_SECONDS);
        detailedBerlinTime = new DetailedBerlinTime();
        request = new BerlinClockRequest();
    }

    @Test
    @DisplayName("Rest API to convert the time to Berlin Time")
    public void convertTime_validRequest_shouldReturnBerlinClockResponse() throws Exception {

        detailedBerlinTime.setSecondsLamp(OFF);
        detailedBerlinTime.setTopFiveHourLamps(FIRST_TWO_LAMPS_RED);
        detailedBerlinTime.setBottomOneHourLamps(ALL_FOUR_LAMPS_RED);
        detailedBerlinTime.setTopFiveMinuteLamps(FIVE_MINT_FOURLAMPON);
        detailedBerlinTime.setBottomOneMinuteLamps(BERLIN_TIME);
        BerlinClockResponse expectedResponse = new BerlinClockResponse(DIGITAL_TIME, detailedBerlinTime, BERLIN_TIME);
        request.setTime(timeInput);

        mockMvc.perform(post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isOk())
                .andExpect(jsonPath("$.berlinTime").value(BERLIN_TIME));
    }

    @Test
    @DisplayName("Rest API should throw exception when invalid input is passed")
    public void convertTime_invalidRequest_shouldReturnBadRequest() throws Exception {

        timeInput = new TimeInput("", TWENTYFOUR_MINUTE, FIVE_SECONDS);
        request.setTime(timeInput);

        mockMvc.perform(
                        post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Rest API should throw internal server error when no input is passed")
    public void convertTime_nullRequest_shouldReturnInternalServerError() throws Exception {

        mockMvc.perform(
                        post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }
}
