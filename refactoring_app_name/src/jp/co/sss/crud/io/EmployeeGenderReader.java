package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

public class EmployeeGenderReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return GENDER_VALID_MSG;
	}

	@Override
	public boolean isValid(String inputString) {
		return inputString.matches(GENDER_VALID_VALUE);
	}

	@Override
	public boolean isParseInt() {
		return true;
	}

}
