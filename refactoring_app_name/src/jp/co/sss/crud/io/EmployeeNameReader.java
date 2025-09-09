package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;
import static jp.co.sss.crud.util.ConstantValue.*;

public class EmployeeNameReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return EMP_NAME_VALID_MSG;
	}

	@Override
	public boolean isValid(String inputString) {
		return inputString.length() >= EMP_NAME_MIN && EMP_NAME_MAX >= inputString.length();
	}

	@Override
	public boolean isParseInt() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
