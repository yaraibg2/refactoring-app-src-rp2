package jp.co.sss.crud.io;

import static jp.co.sss.crud.util.ConstantMsg.*;

import java.util.List;

import jp.co.sss.crud.dto.Employee;

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
		System.out.println(MENU_SYSTEM_NAME);
		System.out.println(MENU_FIND_ALL);
		System.out.println(MENU_FIND_BY_NAME);
		System.out.println(MENU_FIND_BY_DEPT_ID);
		System.out.println(MENU_INSERT_EMP);
		System.out.println(MENU_UPDATE_EMP);
		System.out.println(MENU_DELETE_EMP);
		System.out.println(MENU_END);
		System.out.print(INPUT_MENU_NUMBER);
	}

	/**
	 * 社員を表示
	 * @param employees
	 */
	public static void showEmployees(List<Employee> employees) {
		if (!employees.isEmpty()) {
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
		System.out.println(SYSTEM_END);
	}

	/**
	 * レコードの頭を表示
	 */
	public static void showRecordHead() {
		System.out.println(RECORD_HEAD);
	}

	/**
	 * 該当者がいない旨のメッセージ
	 */
	public static void showNonExistTarget() {
		System.out.println(NO_HIT_EMP);
	}

	/**
	 * 登録処理完了のメッセージ
	 */
	public static void completeInsertMsg() {
		System.out.println(COMPLETE_INSERT);
	}

	/**
	 * 更新処理完了のメッセージ
	 */
	public static void completeUpdateMsg() {
		System.out.println(COMPLETE_UPDATE);
	}

	/**
	 * 更新処理失敗のメッセージ
	 */
	public static void errorUpdateMsg() {
		System.out.println(UPDATE_ERROR);
	}

	/**
	 * 削除処理完了のメッセージ
	 */
	public static void completeDeleteMsg() {
		System.out.println(COMPLETE_DELETE);
	}

	/**
	 * 削除処理失敗のメッセージ
	 */
	public static void errorDeleteMsg() {
		System.out.println(DELETE_ERROR);
	}
}
