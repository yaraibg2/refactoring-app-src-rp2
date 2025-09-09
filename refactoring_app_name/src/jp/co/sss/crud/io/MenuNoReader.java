package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

public class MenuNoReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return MENU_NO_VALID_MSG;
	}

	@Override
	public boolean isValid(String inputString) {
		return inputString.matches(MENU_NO_VALID_VALUE);
	}

	@Override
	public boolean isParseInt() {
		return true;
	}

}
