package jp.co.sss.crud.service;

import jp.co.sss.crud.util.ConstantMsg;

/**
 * メインシステムのサービスクラス
 */
public class EmployeeService {

	/**
	 * メニュー一覧を表示
	 */
	public static void showMenu() {
		System.out.println(ConstantMsg.MENU_SYSTEM_NAME);
		System.out.println(ConstantMsg.MENU_FIND_ALL);
		System.out.println(ConstantMsg.MENU_FIND_BY_NAME);
		System.out.println(ConstantMsg.MENU_FIND_BY_DEPT_ID);
		System.out.println(ConstantMsg.MENU_INSERT_EMP);
		System.out.println(ConstantMsg.MENU_UPDATE_EMP);
		System.out.println(ConstantMsg.MENU_DELETE_EMP);
		System.out.println(ConstantMsg.MENU_END);
		System.out.print(ConstantMsg.INPUT_MENU_NUMBER);
	}
}
