package com.bnpp.kataexam.berlinclock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BerlinClockResponse {

	private DetailedBerlinTime detailedBerlinTime;
	private String berlinTime;
}
