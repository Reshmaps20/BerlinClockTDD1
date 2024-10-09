package com.bnpp.kataexam.berlinclock.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;

public class BerlinClockServiceTest {

	private BerlinClockService berlinClockService;
	private static final String ZERO_HOUR = "00";
	private static final String ONE_HOUR = "01";
	private static final String SIX_HOUR = "06";
	private static final String ZERO_MINUTE = "00";
	private static final String TWO_SECONDS = "02";
	private static final String FIVE_SECONDS = "05";
	private static final String YELLOW = "Y";
	private static final String OFF = "O";
	private static final String FIVE_HOUR_ALLOFF = "OOOO";
	private static final String FIVE_HOUR_FIRSTON = "ROOO";

	@BeforeEach
	public void setup() {
		berlinClockService = new BerlinClockService();
	}

	@Test
	@DisplayName("Seconds Lamp in Berlin Clock should be ON for even seconds")
	public void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

		TimeInput time = new TimeInput(ZERO_HOUR, ZERO_MINUTE, TWO_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(YELLOW));
	}

	@Test
	@DisplayName("Seconds Lamp in Berlin Clock should be OFF for odd seconds")
	public void convertToBerlinTime_passOddSeconds_secondsLampShouldBeOFF() {

		TimeInput time = new TimeInput(ZERO_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(OFF));
	}
	
	@Test
	@DisplayName("Five Hour Lamp in Berlin Clock should be OFF when given hour is less than 5")
	public void convertToBerlinTime_passHoursBetweenOneToFive_allFiveHourLampShouldBeOFF() {

		TimeInput time = new TimeInput(ONE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_ALLOFF));
	}
	
	@Test
	@DisplayName("First Lamp in Five Hour Row should be RED when given hour is between 5 and 9")
	public void convertToBerlinTime_passHoursBetweenFiveToNine_firstLambOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(SIX_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_FIRSTON));
	}
}
