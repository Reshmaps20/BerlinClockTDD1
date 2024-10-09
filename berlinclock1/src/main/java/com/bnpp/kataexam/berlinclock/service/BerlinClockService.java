package com.bnpp.kataexam.berlinclock.service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.bnpp.kataexam.berlinclock.constants.Constants;
import com.bnpp.kataexam.berlinclock.exception.TimeFormatException;
import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.store.Lamp;
import com.bnpp.kataexam.berlinclock.store.LampRow;

@Service
public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		validateTimeValues(time);
		DetailedBerlinTime detailedBerlinTime = new DetailedBerlinTime();
		String berlinTime = calculateBerlinTime(time, detailedBerlinTime);

		return new BerlinClockResponse(detailedBerlinTime, berlinTime);
	}

	private void validateTimeValues(TimeInput time) {
		if (time.getHours() == null || time.getHours().isEmpty()) {
			throw new TimeFormatException(Constants.TIME_IS_EMPTY_ERROR);
		}
	}

	private String calculateBerlinTime(TimeInput time, DetailedBerlinTime detailedBerlinTime) {

		String secondLamp = getSecondsLamp(Integer.parseInt(time.getSeconds()));
		String hourLamp = getHoursLamp(time);
		String oneHourLamp = getOneHourLamp(time);
		String fiveMinuteLamp = getMinuteLamp(time);
		String oneMinuteLamp = getOneMinuteLamp(time);

		setBerlinTimeDetails(detailedBerlinTime, secondLamp, hourLamp, oneHourLamp, fiveMinuteLamp, oneMinuteLamp);

		return Stream.of(secondLamp, hourLamp, oneHourLamp, fiveMinuteLamp, oneMinuteLamp)
				.collect(Collectors.joining(" "));
	}

	private void setBerlinTimeDetails(DetailedBerlinTime detailedBerlinTime, String secondLamp, String hourLamp,
			String oneHourLamp, String fiveMinuteLamp, String oneMinuteLamp) {

		detailedBerlinTime.setSecondsLamp(secondLamp);
		detailedBerlinTime.setTopFiveHourLamps(hourLamp);
		detailedBerlinTime.setBottomOneHourLamps(oneHourLamp);
		detailedBerlinTime.setTopFiveMinuteLamps(fiveMinuteLamp);
		detailedBerlinTime.setBottomOneMinuteLamps(oneMinuteLamp);
	}

	private String getSecondsLamp(int seconds) {
		return (seconds % Constants.SECONDS_DIVIDER == Constants.ZERO) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
	}

	private String getOneMinuteLamp(TimeInput time) {

		int minutes = Integer.parseInt(time.getMinutes());

		return IntStream.range(Constants.ZERO, LampRow.BOTTOM_MINUTE_LAMP.getLength())
				.mapToObj(i -> (i < minutes % Constants.MINUTES_DIVIDER) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());
	}

	private String getMinuteLamp(TimeInput time) {

		int minutes = Integer.parseInt(time.getMinutes());

		String mintLamps = IntStream.range(Constants.ZERO, LampRow.TOP_MINUTE_LAMP.getLength())
				.mapToObj(i -> (i < minutes / Constants.MINUTES_DIVIDER) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

		return mintLamps.replace(Constants.REPLACE_YYY, Constants.REPLACE_TO_YYR);
	}

	private String getOneHourLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(Constants.ZERO, LampRow.BOTTOM_HOUR_LAMP.getLength())
				.mapToObj(i -> (i < hours % Constants.HOUR_DIVIDER) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());
	}

	private String getHoursLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(0, LampRow.TOP_HOUR_LAMP.getLength())
				.mapToObj(i -> (i < hours / 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

	}
}
