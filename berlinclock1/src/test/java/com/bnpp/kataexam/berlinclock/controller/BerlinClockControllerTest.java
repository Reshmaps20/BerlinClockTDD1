package com.bnpp.kataexam.berlinclock.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.bnpp.kataexam.berlinclock.model.BerlinClockRequest;
import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.service.BerlinClockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.bnpp.kataexam.berlinclock.constants.TestConstants.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BerlinClockControllerTest {

	private TimeInput timeInput;
	private DetailedBerlinTime detailedBerlinTime;
	private BerlinClockRequest request;

	@MockBean
	private BerlinClockService berlinClockService;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		timeInput = new TimeInput(FOURTEEN_HOUR, TWENTYFOUR_MINUTE, FIVE_SECONDS);
		detailedBerlinTime = new DetailedBerlinTime();
		request = new BerlinClockRequest();
	}

	@Test
	public void convertTime_validRequest_shouldReturnBerlinClockResponse() throws Exception {

		detailedBerlinTime.setSecondsLamp(OFF);
		detailedBerlinTime.setTopFiveHourLamps(FIRST_TWO_LAMPS_RED);
		detailedBerlinTime.setBottomOneHourLamps(ALL_FOUR_LAMPS_RED);
		detailedBerlinTime.setTopFiveMinuteLamps(FIVE_MINT_FOURLAMPON);
		detailedBerlinTime.setBottomOneMinuteLamps(BERLIN_TIME);
		BerlinClockResponse expectedResponse = new BerlinClockResponse(DIGITAL_TIME, detailedBerlinTime, BERLIN_TIME);
		request.setTime(timeInput);

		when(berlinClockService.convertToBerlinTime(any(TimeInput.class))).thenReturn(expectedResponse);

		mockMvc.perform(post("/api/berlinclock/convert").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.berlinTime").value(BERLIN_TIME));
	}

}
