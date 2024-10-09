package com.bnpp.kataexam.berlinclock.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BerlinClockServiceTest {

	private BerlinClockService berlinClockService;

	@BeforeEach
	public void setup() {
		berlinClockService = new BerlinClockService();
	}

	@Test
	@DisplayName("Seconds Lamp in Berlin Clock should be ON for even seconds")
	public void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

		String result = berlinClockService.convertToBerlinTime("00:00:02");
		assertEquals("Y", result);
	}
}
