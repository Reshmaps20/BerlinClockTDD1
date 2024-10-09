package com.bnpp.kataexam.berlinclock.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.bnpp.kataexam.berlinclock.exception.TimeFormatException;
import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.validation.TimeValidator;

public class BerlinClockServiceTest {

	private BerlinClockService berlinClockService;
	private TimeValidator timeValidator;
	private static final String ZERO_HOUR = "00";
	private static final String ONE_HOUR = "01";
	private static final String FIVE_HOUR = "05";
	private static final String SIX_HOUR = "06";
	private static final String TWELVE_HOUR = "12";
	private static final String FOURTEEN_HOUR = "14";
	private static final String EIGHTEEN_HOUR = "18";
	private static final String TWENTYTHREE_HOUR = "23";
	private static final String THIRTY_HOUR = "30";
	private static final String ZERO_MINUTE = "00";
	private static final String TWO_MINUTE = "02";
	private static final String SIX_MINUTE = "06";
	private static final String TWELVE_MINUTE = "12";
	private static final String FIFTEEN_MINUTE = "15";
	private static final String EIGHTEEN_MINUTE = "18";
	private static final String TWENTY_MINUTE = "20";
	private static final String TWENTYFOUR_MINUTE = "24";
	private static final String TWO_SECONDS = "02";
	private static final String FIVE_SECONDS = "05";
	private static final String YELLOW = "Y";
	private static final String OFF = "O";
	private static final String FOUR_LAMPS_OFF = "OOOO";
	private static final String FIRST_LAMP_RED = "ROOO";
	private static final String FIRST_TWO_LAMPS_RED = "RROO";
	private static final String FIRST_THREE_LAMPS_RED = "RRRO";
	private static final String ALL_FOUR_LAMPS_RED = "RRRR";
	private static final String ALL_11_LAMPS_OFF = "OOOOOOOOOOO";
	private static final String ONE_LAMP_YELLOW_OUT_OF_ELEVEN = "YOOOOOOOOOO";
	private static final String TWO_LAMP_YELLOW_OUT_OF_ELEVEN = "YYOOOOOOOOO";
	private static final String THIRD_LAMP_RED_OUT_OF_ELEVEN = "YYROOOOOOOO";
	private static final String FIVE_MINT_FOURLAMPON = "YYRYOOOOOOO";
	private static final String FIRST_LAMP_YELLOW = "YOOO";
	private static final String FIRST_TWO_LAMPS_YELLOW = "YYOO";
	private static final String FIRST_THREE_LAMPS_YELLOW = "YYYO";
	private static final String ALL_FOUR_LAMPS_YELLOW = "YYYY";
	
	@BeforeEach
	public void setup() {
		timeValidator = new TimeValidator();
		berlinClockService = new BerlinClockService(timeValidator);
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
		assertTrue(response.getBerlinTime().contains(FOUR_LAMPS_OFF));
	}

	@Test
	@DisplayName("First Lamp in Five Hour Row should be RED when given hour is between 5 and 9")
	public void convertToBerlinTime_passHoursBetweenFiveToNine_firstLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(SIX_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIRST_LAMP_RED));
	}

	@Test
	@DisplayName("First Two Lamp in Five Hour Row should be RED when given hour is between 10 and 14")
	public void convertToBerlinTime_passHoursBetweenTenToFourteen_firstTwoLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(TWELVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIRST_TWO_LAMPS_RED));
	}

	@Test
	@DisplayName("First Three Lamp in Five Hour Row should be RED when given hour is between 15 and 19")
	public void convertToBerlinTime_passHoursBetweenFifteenToNinteen_firstThreeLampOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(EIGHTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(FIRST_THREE_LAMPS_RED));
	}

	@Test
	@DisplayName("All Lamps in Five Hour Row should be RED when given hour is between 20 and 23")
	public void convertToBerlinTime_passHoursBetweenTwentyToTwentyThree_allLampsOfFiveHourRowShouldBeRED() {

		TimeInput time = new TimeInput(TWENTYTHREE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertTrue(response.getBerlinTime().contains(ALL_FOUR_LAMPS_RED));
	}

	@Test
	@DisplayName("One Hour Lamp in Berlin Clock should be OFF when given hour is divisible by 5")
	public void convertToBerlinTime_passHoursDivisibleByFive_allOneHourLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2], FOUR_LAMPS_OFF);
	}

	@Test
	@DisplayName("First Lamp in One Hour Row should be RED when hour divided by 5 has reminder 1")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderOne_firstLampShouldBeRED() {

		TimeInput time = new TimeInput(SIX_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2], FIRST_LAMP_RED);
	}

	@Test
	@DisplayName("First Two Lamp in One Hour Row should be RED when hour divided by 5 has reminder 2")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderTwo_firstTwoLampShouldBeRED() {

		TimeInput time = new TimeInput(TWELVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2], FIRST_TWO_LAMPS_RED);
	}

	@Test
	@DisplayName("First Three Lamp in One Hour Row should be RED when hour divided by 5 has reminder 3")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderThree_firstThreeLampShouldBeRED() {

		TimeInput time = new TimeInput(EIGHTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2], FIRST_THREE_LAMPS_RED);
	}

	@Test
	@DisplayName("All Lamp in One Hour Row should be RED when hour divided by 5 has reminder 4")
	public void convertToBerlinTime_whenHourDividedByFiveHasRemainderFour_allLampShouldBeRED() {

		TimeInput time = new TimeInput(FOURTEEN_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[2], ALL_FOUR_LAMPS_RED);
	}

	@Test
	@DisplayName("Five Minute Lamp in Berlin Clock should be OFF when given minute is less than 5")
	public void convertToBerlinTime_passMinutesLessThanFive_allFiveMinuteLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3], ALL_11_LAMPS_OFF);
	}

	@Test
	@DisplayName("First Lamp in Five Minute Row should be YELLOW when given minute is between 5 and 10")
	public void convertToBerlinTime_passMinutesBetweenFiveAndTen_firstLampOfFiveMinuteLampShouldBeYellow() {

		TimeInput time = new TimeInput(FIVE_HOUR, SIX_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3], ONE_LAMP_YELLOW_OUT_OF_ELEVEN);
	}

	@Test
	@DisplayName("First Two Lamp in Five Minute Row should be YELLOW when given minute is between 10 and 15")
	public void convertToBerlinTime_passMinutesBetweenTenAndFifteen_firstTwoLampOfFiveMinuteLampShouldBeYellow() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWELVE_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3], TWO_LAMP_YELLOW_OUT_OF_ELEVEN);
	}

	@Test
	@DisplayName("Third Lamp in Five Minute Row should be RED when given minute is 15")
	public void convertToBerlinTime_passMinutesFifteen_fiveMinuteRowThirdLampShouldBeRed() {

		TimeInput time = new TimeInput(FIVE_HOUR, FIFTEEN_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3], THIRD_LAMP_RED_OUT_OF_ELEVEN);
	}

	@Test
	@DisplayName("Lamps in Five Minute Row should be illuminated correctly for the minute divisible by 5")
	public void convertToBerlinTime_minutesDivisibleByFive_shouldIlluminateCorrectLamps() {

		TimeInput time = new TimeInput(FIVE_HOUR, TWENTY_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[3], FIVE_MINT_FOURLAMPON);
	}

	@Test
	@DisplayName("One Minute Lamp in Berlin Clock should be OFF when given minute is divisible by 5")
	public void convertToBerlinTime_passMinuteDivisibleByFive_allOneMinuteLampShouldBeOFF() {

		TimeInput time = new TimeInput(FIVE_HOUR, ZERO_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[4], FOUR_LAMPS_OFF);
	}

	@Test
	@DisplayName("First Lamp in One Minute Row should be Yellow when minute divided by 5 has reminder 1")
	public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderOne_firstLampShouldBeYellow() {

		TimeInput time = new TimeInput(SIX_HOUR, SIX_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[4], FIRST_LAMP_YELLOW);
	}

	@Test
	@DisplayName("First Two Lamp in One Minute Row should be Yellow when minute divided by 5 has reminder 2")
	public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderTwo_firstTwoLampShouldBeYellow() {

		TimeInput time = new TimeInput(TWELVE_HOUR, TWELVE_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getBerlinTime().split(" ")[4], FIRST_TWO_LAMPS_YELLOW);
	}

	@Test
	@DisplayName("First Three Lamp in One Minute Row should be Yellow when minute divided by 5 has reminder 3")
	public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderThree_firstThreeLampShouldBeYellow() {

		TimeInput time = new TimeInput(EIGHTEEN_HOUR, EIGHTEEN_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getDetailedBerlinTime().getBottomOneMinuteLamps(), FIRST_THREE_LAMPS_YELLOW);
	}

	@Test
	@DisplayName("All Lamp in One Minute Row should be Yellow when minute divided by 5 has reminder 4")
	public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderFour_allLampShouldBeYellow() {

		TimeInput time = new TimeInput(FOURTEEN_HOUR, TWENTYFOUR_MINUTE, FIVE_SECONDS);
		BerlinClockResponse response = berlinClockService.convertToBerlinTime(time);
		assertEquals(response.getDetailedBerlinTime().getBottomOneMinuteLamps(), ALL_FOUR_LAMPS_YELLOW);
	}

	@Test
	@DisplayName("Input Hours should not be empty")
	public void convertToBerlinTime_checkWhetherTheInputHoursAreNotEmpty_shouldThrowTimeFormatException() {

		TimeInput time = new TimeInput("", TWENTYFOUR_MINUTE, FIVE_SECONDS);
		assertThrows(TimeFormatException.class, () -> {
			berlinClockService.convertToBerlinTime(time);
		});
	}
	
	@Test
	@DisplayName("Input Minutes should not be empty")
	public void convertToBerlinTime_checkWhetherTheInputMinutesAreNotEmpty_shouldThrowTimeFormatException() {

		TimeInput time = new TimeInput(FOURTEEN_HOUR, "", FIVE_SECONDS);
		assertThrows(TimeFormatException.class, () -> {
			berlinClockService.convertToBerlinTime(time);
		});
	}
	
	@Test
	@DisplayName("Input Seconds should not be empty")
	public void convertToBerlinTime_checkWhetherTheInputSecondsAreNotEmpty_shouldThrowTimeFormatException() {

		TimeInput time = new TimeInput(FOURTEEN_HOUR, TWENTYFOUR_MINUTE, "");
		assertThrows(TimeFormatException.class, () -> {
			berlinClockService.convertToBerlinTime(time);
		});
	}
	
	@Test
	@DisplayName("Input Hours must be between 0 and 23")
	public void convertToBerlinTime_checkWhetherTheHourIsValid_shouldThrowTimeFormatException() {

		TimeInput time = new TimeInput(THIRTY_HOUR, TWENTYFOUR_MINUTE, FIVE_SECONDS);
		assertThrows(TimeFormatException.class, () -> {
			berlinClockService.convertToBerlinTime(time);
		});
	}
}
