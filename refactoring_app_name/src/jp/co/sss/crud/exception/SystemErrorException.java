package jp.co.sss.crud.exception;

public class SystemErrorException extends Exception {

	/**
	 * 独自例外の送出
	 * @param errorMsg
	 */
	public SystemErrorException(String errorMsg) {
		super(errorMsg);
	}

	/**
	 * 独自例外の送出 原因つき
	 * @param errorMsg
	 * @param cause
	 */
	public SystemErrorException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
	}
}
