package com.bnpp.kataexam.berlinclock.validation;

import com.bnpp.kataexam.berlinclock.constants.Constants;
import com.bnpp.kataexam.berlinclock.exception.TimeFormatException;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TimeValidator {

	public void validateTimeValues(TimeInput time) {

		if (isAnyTimeFieldInvalid(time)) {
			throw new TimeFormatException(Constants.TIME_IS_EMPTY_ERROR);
		}
		if(Integer.parseInt(time.getHours()) < 0 || Integer.parseInt(time.getHours()) > 23) {
			throw new TimeFormatException(Constants.INVALID_HOUR_ERROR);
		}
		if(Integer.parseInt(time.getMinutes()) < 0 || Integer.parseInt(time.getMinutes()) > 59) {
			throw new TimeFormatException(Constants.INVALID_MINUTE_ERROR);
		}
		if(Integer.parseInt(time.getSeconds()) < 0 || Integer.parseInt(time.getSeconds()) > 59) {
			throw new TimeFormatException(Constants.INVALID_SECOND_ERROR);
		}
		
		
	}

	private boolean isAnyTimeFieldInvalid(TimeInput time) {
		return StringUtils.isEmpty(time.getHours()) || StringUtils.isEmpty(time.getMinutes())
				|| StringUtils.isEmpty(time.getSeconds());
	}
}
