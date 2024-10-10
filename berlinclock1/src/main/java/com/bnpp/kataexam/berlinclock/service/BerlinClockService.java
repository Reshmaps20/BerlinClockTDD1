package com.bnpp.kataexam.berlinclock.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.bnpp.kataexam.berlinclock.constants.Constants;
import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.store.Lamp;
import com.bnpp.kataexam.berlinclock.store.LampRow;
import com.bnpp.kataexam.berlinclock.validation.TimeValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BerlinClockService {

	private final TimeValidator timeValidator;

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		timeValidator.validateTimeValues(time);
		Map<String, String> lamps = calculateLamps(time);

		return BerlinClockResponse.builder()
				.digitalTime(convertToDigitalTime(time))
				.detailedBerlinTime(createDetailedBerlinTime(lamps))
				.berlinTime(calculateBerlinTime(lamps))
				.build();
	}

	private String calculateBerlinTime(Map<String, String> lamps) {

		return Stream.of(
						lamps.get(LampRow.SECONDS_LAMP.getName()),
						lamps.get(LampRow.TOP_HOUR_LAMP.getName()),
						lamps.get(LampRow.BOTTOM_HOUR_LAMP.getName()),
						lamps.get(LampRow.TOP_MINUTE_LAMP.getName()),
						lamps.get(LampRow.BOTTOM_MINUTE_LAMP.getName())
				)
				.collect(Collectors.joining(" "));
	}

	private DetailedBerlinTime createDetailedBerlinTime(Map<String, String> lamps) {

		return DetailedBerlinTime.builder()
				.secondsLamp(lamps.get(LampRow.SECONDS_LAMP.getName()))
				.topFiveHourLamps(lamps.get(LampRow.TOP_HOUR_LAMP.getName()))
				.bottomOneHourLamps(lamps.get(LampRow.BOTTOM_HOUR_LAMP.getName()))
				.topFiveMinuteLamps(lamps.get(LampRow.TOP_MINUTE_LAMP.getName()))
				.bottomOneMinuteLamps(lamps.get(LampRow.BOTTOM_MINUTE_LAMP.getName()))
				.build();
	}

	private Map<String,String> calculateLamps(TimeInput time) {

		Map<String, String> lamps = new HashMap<>();
		int hours = Integer.parseInt(time.getHours());
		int minutes = Integer.parseInt(time.getMinutes());
		int seconds = Integer.parseInt(time.getSeconds());

		lamps.put(LampRow.SECONDS_LAMP.getName(), getSecondsLamp(seconds));
		lamps.put(LampRow.TOP_HOUR_LAMP.getName(), getHourLampRow(LampRow.TOP_HOUR_LAMP.getLength(), hours / Constants.HOUR_DIVIDER));
		lamps.put(LampRow.BOTTOM_HOUR_LAMP.getName(), getHourLampRow(LampRow.BOTTOM_HOUR_LAMP.getLength(), hours % Constants.HOUR_DIVIDER));
		lamps.put(LampRow.TOP_MINUTE_LAMP.getName(), getMinuteLampRow(LampRow.TOP_MINUTE_LAMP.getLength(), minutes / Constants.MINUTES_DIVIDER, true) );
		lamps.put(LampRow.BOTTOM_MINUTE_LAMP.getName(), getMinuteLampRow(LampRow.BOTTOM_MINUTE_LAMP.getLength(), minutes % Constants.MINUTES_DIVIDER, false));

		return lamps;
	}

	private String getSecondsLamp(int seconds) {
		return (seconds % Constants.SECONDS_DIVIDER == Constants.ZERO) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
	}

	private String getMinuteLampRow(int rowLength, int minuteValue, boolean isTopRow) {
		String mintLamps = IntStream.range(Constants.ZERO, rowLength)
				.mapToObj(lampIndex -> (lampIndex < minuteValue) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

		return isTopRow ? mintLamps.replace(Constants.REPLACE_YYY, Constants.REPLACE_TO_YYR) : mintLamps;
	}

	private String getHourLampRow(int rowLength, int hourValue) {
		return IntStream.range(Constants.ZERO, rowLength)
				.mapToObj(lampIndex -> (lampIndex  < hourValue) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());
	}
	
	private String convertToDigitalTime(TimeInput time) {
		return Arrays.stream(new int[] { Integer.parseInt(time.getHours()), Integer.parseInt(time.getMinutes()),
						Integer.parseInt(time.getSeconds()) })
				.mapToObj(timeValue -> String.format(Constants.TIME_FORMAT, timeValue))
				.collect(Collectors.joining(Constants.TIME_SEPARATOR));
	}
}
