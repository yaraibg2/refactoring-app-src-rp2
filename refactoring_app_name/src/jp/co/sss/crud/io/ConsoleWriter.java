package jp.co.sss.crud.io;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.ConstantMsg;

public class ConsoleWriter {
	/**
	 * インスタンス化の禁止
	 */
	private ConsoleWriter() {
	}

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

	/**
	 * 社員を表示
	 * @param employees
	 */
	public static void showEmployees(List<Employee> employees) {
		if (employees != null) {
			ConsoleWriter.showRecordHead();
			employees.forEach(System.out::println);
		} else {
			ConsoleWriter.showNonExistTarget();
		}
	}

	/**
	 * 終了メッセージの表示
	 */
	public static void systemEnd() {
		System.out.println(ConstantMsg.SYSTEM_END);
	}

	/**
	 * レコードの頭を表示
	 */
	public static void showRecordHead() {
		System.out.println(ConstantMsg.RECORD_HEAD);
	}

	/**
	 * 該当者がいない旨のメッセージ
	 */
	public static void showNonExistTarget() {
		System.out.println(ConstantMsg.NO_HIT_EMP);
	}

	/**
	 * 登録処理完了のメッセージ
	 */
	public static void completeInsertMsg() {
		System.out.println(ConstantMsg.COMPLETE_INSERT);
	}

	/**
	 * 更新処理完了のメッセージ
	 */
	public static void completeUpdateMsg() {
		System.out.println(ConstantMsg.COMPLETE_UPDATE);
	}

	public static void completeDeleteMsg() {
		System.out.println(ConstantMsg.COMPLETE_DELETE);
	}
}
