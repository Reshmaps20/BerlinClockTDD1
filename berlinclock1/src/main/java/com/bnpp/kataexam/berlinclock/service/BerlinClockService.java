package com.bnpp.kataexam.berlinclock.service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.store.Lamp;

public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		DetailedBerlinTime detailedBerlinTime = new DetailedBerlinTime();
		String berlinTime = calculateBerlinTime(time, detailedBerlinTime);

		return new BerlinClockResponse(detailedBerlinTime, berlinTime);
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
		return (seconds % 2 == 0) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
	}

	private String getOneMinuteLamp(TimeInput time) {

		int minutes = Integer.parseInt(time.getMinutes());
		if (minutes % 5 == 1)
			return "YOOO";
		else if (minutes % 5 == 2)
			return "YYOO";
		else if (minutes % 5 == 3)
			return "YYYO";
		else
			return "OOOO";
	}

	private String getMinuteLamp(TimeInput time) {

		int minutes = Integer.parseInt(time.getMinutes());

		String mintLamps = IntStream.range(0, 11)
				.mapToObj(i -> (i < minutes / 5) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

		return mintLamps.replace("YYY", "YYR");
	}

	private String getOneHourLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(0, 4).mapToObj(i -> (i < hours % 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());
	}

	private String getHoursLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(0, 4).mapToObj(i -> (i < hours / 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

	}
}
