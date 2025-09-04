package jp.co.sss.crud.service;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;

/**
 * 削除用のサービスクラス
 */
public class EmployeeDeleteService {
	/**
	 * 社員を削除
	 */
	public static void deleteEmp() {
		// 削除する社員IDを入力
		System.out.print(ConstantMsg.INPUT_DELETE);

		// 削除機能の呼出
		DBController.delete();
	}
}
