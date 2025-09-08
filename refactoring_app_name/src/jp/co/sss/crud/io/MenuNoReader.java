package jp.co.sss.crud.io;

public class MenuNoReader implements IConsoleReader {

	@Override
	public String getErrorMsg() {
		return "1以上7以下の整数を入力してください";
	}

	@Override
	public boolean isValid(String inputString) {
		return inputString.matches("^[1-7 １-７]$");
	}

	@Override
	public boolean isParseInt() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
