package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

public class EmployeeDeptIdReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return DEPT_ID_VALID_MSG;
	}

	@Override
	public boolean isValid(String inputString) {
		return inputString.matches(DEPT_ID_VALID_VALUE);
	}

	@Override
	public boolean isParseInt() {
		return true;
	}

}
