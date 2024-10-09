package com.bnpp.kataexam.berlinclock.service;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;

public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		String seconds = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";
		String hour = null;
		if (Integer.parseInt(time.getHours()) >= 5 && Integer.parseInt(time.getHours()) <= 9) {
			hour = "ROOO";
		} else {
			hour = "OOOO";
		}
		return new BerlinClockResponse(String.join(" ", seconds, hour));
	}
}
