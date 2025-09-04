package jp.co.sss.crud.service;

import java.sql.SQLException;

import jp.co.sss.crud.db.DBController;

/**
 * 全件検索用のサービスクラス
 */
public class EmployeeAllFindService {
	/**
	 * 全件検索
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void findAll() throws ClassNotFoundException, SQLException {
		// 全件表示機能の呼出
		DBController.findAll();
	}
}
