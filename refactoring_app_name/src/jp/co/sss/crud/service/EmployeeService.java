package jp.co.sss.crud.service;

import jp.co.sss.crud.util.Constants;

/**
 * メインシステムのサービスクラス
 */
public class EmployeeService {

	/**
	 * メニュー一覧を表示
	 */
	public static void showMenu() {
		System.out.println(Constants.MENU_SYSTEM_NAME);
		System.out.println(Constants.MENU_FIND_ALL);
		System.out.println(Constants.MENU_FIND_BY_NAME);
		System.out.println(Constants.MENU_FIND_BY_DEPT_ID);
		System.out.println(Constants.MENU_INSERT_EMP);
		System.out.println(Constants.MENU_UPDATE_EMP);
		System.out.println(Constants.MENU_DELETE_EMP);
		System.out.println(Constants.MENU_END);
		System.out.print(Constants.INPUT_MENU_NUMBER);
	}
}
