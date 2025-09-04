package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.Constants;

/**
 * 主キー検索用のサービスクラス
 */
public class EmployeeFindByDeptIdService {
	/**
	 * 社員をIDで検索
	 * @param br
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void findByDeptId(BufferedReader br) throws ClassNotFoundException, SQLException, IOException {
		// 検索する部署IDを入力
		System.out.print(Constants.FIND_BY_DEPT_ID);
		String deptId = br.readLine();

		// 検索機能の呼出
		DBController.findByDeptId(deptId);
	}
}
