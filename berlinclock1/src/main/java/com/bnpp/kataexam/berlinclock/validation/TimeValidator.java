package com.bnpp.kataexam.berlinclock.validation;

import com.bnpp.kataexam.berlinclock.constants.Constants;
import com.bnpp.kataexam.berlinclock.exception.TimeFormatException;
import com.bnpp.kataexam.berlinclock.model.TimeInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TimeValidator {

	public void validateTimeValues(TimeInput time) {

		if (StringUtils.isEmpty(time.getHours()) || StringUtils.isEmpty(time.getMinutes())) {
			throw new TimeFormatException(Constants.TIME_IS_EMPTY_ERROR);
		}
	}

}
