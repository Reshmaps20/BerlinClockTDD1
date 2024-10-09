package com.bnpp.kataexam.berlinclock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeInput {

	private String hours;
	private String minutes;
	private String seconds;
}
