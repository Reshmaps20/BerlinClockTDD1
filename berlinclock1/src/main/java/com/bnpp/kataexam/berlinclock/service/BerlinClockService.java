package com.bnpp.kataexam.berlinclock.service;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;

public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		String secondLamp = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";
		String hourLamp = getHoursLamp(time);
		return new BerlinClockResponse(String.join(" ", secondLamp, hourLamp));
	}

	private String getHoursLamp(TimeInput time) {

		int hours = Integer.parseInt(time.getHours());
		if (hours >= 5 && hours <= 9) {
			return "ROOO";
		} else {
			return "OOOO";
		}
	}
}
