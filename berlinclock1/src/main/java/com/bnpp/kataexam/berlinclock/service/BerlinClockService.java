package com.bnpp.kataexam.berlinclock.service;

import com.bnpp.kataexam.berlinclock.model.BerlinClockResponse;
import com.bnpp.kataexam.berlinclock.model.TimeInput;

public class BerlinClockService {

	public BerlinClockResponse convertToBerlinTime(TimeInput time) {

		if (Integer.parseInt(time.getSeconds()) % 2 == 0) {
			return new BerlinClockResponse("Y");
		} else
			return new BerlinClockResponse("O");
	}
}
