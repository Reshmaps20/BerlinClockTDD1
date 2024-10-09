package com.bnpp.kataexam.berlinclock.service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;

public class BerlinClockService {

	public static final String RED = "R";
	public static final String OFF = "O";

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		String secondLamp = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";
		String hourLamp = getHoursLamp(time);
		return new BerlinClockResponse(String.join(" ", secondLamp, hourLamp));
	}

	private String getHoursLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		return IntStream.range(0, 4).mapToObj(i -> (i < hours / 5) ? RED : OFF).collect(Collectors.joining());

	}
}
