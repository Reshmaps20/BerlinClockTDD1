package com.bnpp.kataexam.berlinclock.service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import com.bnpp.kataexam.berlinclock.store.Lamp;

public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		String secondLamp = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
		String hourLamp = getHoursLamp(time);
		String oneHourLamp = getOneHourLamp(time);
		String fiveMinuteLamp = getMinuteLamp(time);
		String oneMinuteLamp = getOneMinuteLamp(time);
		return new BerlinClockResponse(String.join(" ", secondLamp, hourLamp,oneHourLamp,fiveMinuteLamp, oneMinuteLamp));
	}

	private String getOneMinuteLamp(TimeInput time) {
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
		return IntStream.range(0, 4)
				.mapToObj(i -> (i < hours % 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());
	}

	private String getHoursLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(0, 4).mapToObj(i -> (i < hours / 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
				.collect(Collectors.joining());

	}
}
