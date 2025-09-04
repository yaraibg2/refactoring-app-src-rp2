package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.Constants;

/**
 * 更新用のサービスクラス
 */
public class EmployeeUpdateService {
	/**
	 * 社員情報の更新
	 * @param br
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void updateEmp(BufferedReader br)
			throws IOException, ClassNotFoundException, SQLException, ParseException {
		// 更新する社員IDを入力
		System.out.print(Constants.INPUT_UPDATE);

		// 更新する値を入力する
		String inputEmpId = br.readLine();
		Integer.parseInt(inputEmpId);

		// 更新機能の呼出
		DBController.update(inputEmpId);
		System.out.println(Constants.COMPLETE_UPDATE);
	}
}
