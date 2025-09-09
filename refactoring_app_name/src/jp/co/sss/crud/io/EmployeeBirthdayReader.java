package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeBirthdayReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return DATE_VALID_MSG;
	}

	@Override
	public boolean isValid(String inputString) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setLenient(false);

		try {
			sdf.parse(inputString);
		} catch (ParseException | IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isParseInt() {
		return false;
	}

}
