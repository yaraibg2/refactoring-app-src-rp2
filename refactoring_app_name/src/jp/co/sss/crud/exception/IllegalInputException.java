package jp.co.sss.crud.exception;

public class IllegalInputException extends Exception {

	/**
	 * 独自例外の送出
	 * @param errorMsg
	 */
	public IllegalInputException(String errorMsg) {
		super(errorMsg);
	}

}
