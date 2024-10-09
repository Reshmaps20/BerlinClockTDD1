package com.bnpp.kataexam.berlinclock.model;

public class BerlinClockResponse {

	private String berlinTime;

	public BerlinClockResponse(String berlinTime) {
		super();
		this.berlinTime = berlinTime;
	}

	public String getBerlinTime() {
		return berlinTime;
	}

	public void setBerlinTime(String berlinTime) {
		this.berlinTime = berlinTime;
	}

}
