package jp.co.sss.crud.service;

import java.io.IOException;
import java.sql.SQLException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;

/**
 * 名前検索用のサービスクラス
 */
public class EmployeeFindByEmpNameService {
	/**
	 * 社員を名前で検索
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void findByName() throws ClassNotFoundException, SQLException, IOException {
		// 社員名検索
		System.out.print(ConstantMsg.INPUT_EMP_NAME);

		// 検索機能の呼出
		DBController.findByName();
	}
}
