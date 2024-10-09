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
	private static final String FIVE_HOUR = "05";
	private static final String SIX_HOUR = "06";
	private static final String TWELVE_HOUR = "12";
	private static final String FOURTEEN_HOUR = "14";
	private static final String EIGHTEEN_HOUR = "18";
	private static final String TWENTYTHREE_HOUR = "23";
	private static final String ZERO_MINUTE = "00";
	private static final String ONE_MINUTE = "01";
	private static final String TWO_MINUTE = "02";
	private static final String SIX_MINUTE = "06";
	private static final String TWELVE_MINUTE = "12";
	private static final String FIFTEEN_MINUTE = "15";
	private static final String TWENTY_MINUTE = "20";
	private static final String TWO_SECONDS = "02";
	private static final String FIVE_SECONDS = "05";
	private static final String YELLOW = "Y";
	private static final String OFF = "O";
	private static final String FIVE_HOUR_ALLOFF = "OOOO";
	private static final String FIVE_HOUR_FIRSTON = "ROOO";
	private static final String FIVE_HOUR_FIRSTTWOON = "RROO";
	private static final String FIVE_HOUR_FIRSTTHREEON = "RRRO";
	private static final String FIVE_HOUR_ALLON = "RRRR";
	private static final String FIVE_MINT_ALLOFF = "OOOOOOOOOOO";
	private static final String FIVE_MINT_FIRSTON = "YOOOOOOOOOO";
	private static final String FIVE_MINT_FIRSTTWOON = "YYOOOOOOOOO";
	private static final String FIVE_MINT_THIRDRED = "YYROOOOOOOO";
	private static final String FIVE_MINT_FOURLAMPON = "YYRYOOOOOOO";


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
	public void convertToBerlinTime_passHoursBetweenFiveToNine_firstLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(SIX_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_FIRSTON));
	}
	
	@Test
	@DisplayName("First Two Lamp in Five Hour Row should be RED when given hour is between 10 and 14")
	public void convertToBerlinTime_passHoursBetweenTenToFourteen_firstTwoLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(TWELVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_FIRSTTWOON));
	}
	
	@Test
	@DisplayName("First Three Lamp in Five Hour Row should be RED when given hour is between 15 and 19")
	public void convertToBerlinTime_passHoursBetweenFifteenToNinteen_firstThreeLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(EIGHTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_FIRSTTHREEON));
	}
	
	@Test
	@DisplayName("All Lamps in Five Hour Row should be RED when given hour is between 20 and 23")
	public void convertToBerlinTime_passHoursBetweenTwentyToTwentyThree_allLampsOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(TWENTYTHREE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIVE_HOUR_ALLON));
	}
	
	@Test
	@DisplayName("One Hour Lamp in Berlin Clock should be OFF when given hour is divisible by 5")
	public void convertToBerlinTime_passHoursDivisibleByFive_allOneHourLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2],FIVE_HOUR_ALLOFF);
	}
	
	@Test
	@DisplayName("First Lamp in One Hour Row should be RED when hour divided by 5 has reminder 1")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderOne_firstLampShouldBeRED() {

		TimeInput time = new TimeInput(SIX_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2],FIVE_HOUR_FIRSTON);
	}
	
	@Test
	@DisplayName("First Two Lamp in One Hour Row should be RED when hour divided by 5 has reminder 2")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderTwo_firstTwoLampShouldBeRED() {

		TimeInput time = new TimeInput(TWELVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2],FIVE_HOUR_FIRSTTWOON);
	}
	
	@Test
	@DisplayName("First Three Lamp in One Hour Row should be RED when hour divided by 5 has reminder 3")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderThree_firstThreeLampShouldBeRED() {

		TimeInput time = new TimeInput(EIGHTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2],FIVE_HOUR_FIRSTTHREEON);
	}
	
	@Test
	@DisplayName("All Lamp in One Hour Row should be RED when hour divided by 5 has reminder 4")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderFour_allLampShouldBeRED() {

		TimeInput time = new TimeInput(FOURTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2],FIVE_HOUR_ALLON);
	}
	
	@Test
	@DisplayName("Five Minute Lamp in Berlin Clock should be OFF when given minute is less than 5")
	public void convertToBerlinTime_passMinutesLessThanFive_allFiveMinuteLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3],FIVE_MINT_ALLOFF);
	}
	
	@Test
	@DisplayName("First Lamp in Five Minute Row should be YELLOW when given minute is between 5 and 10")
	public void convertToBerlinTime_passMinutesBetweenFiveAndTen_firstLampOfFiveMinuteLampShouldBeYellow() {

		TimeInput time = new TimeInput(FIVE_HOUR, SIX_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3],FIVE_MINT_FIRSTON);
	}
	
	@Test
	@DisplayName("First Two Lamp in Five Minute Row should be YELLOW when given minute is between 10 and 15")
	public void convertToBerlinTime_passMinutesBetweenTenAndFifteen_firstTwoLampOfFiveMinuteLampShouldBeYellow() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWELVE_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3],FIVE_MINT_FIRSTTWOON);
	}
	
	@Test
	@DisplayName("Third Lamp in Five Minute Row should be RED when given minute is 15")
	public void convertToBerlinTime_passMinutesFifteen_fiveMinuteRowThirdLampShouldBeRed() {

		TimeInput time = new TimeInput(FIVE_HOUR, FIFTEEN_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3],FIVE_MINT_THIRDRED);
	}
	
	@Test
	@DisplayName("Lamps in Five Minute Row should be illuminated correctly for the minute divisible by 5")
	public void convertToBerlinTime_minutesDivisibleByFive_shouldIlluminateCorrectLamps() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWENTY_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3],FIVE_MINT_FOURLAMPON);
	}
	
	@Test
	@DisplayName("One Minute Lamp in Berlin Clock should be OFF when given minute is divisible by 5")
	public void convertToBerlinTime_passMinuteDivisibleByFive_allOneMinuteLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, ONE_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[4],FIVE_HOUR_ALLOFF);
	}
}
