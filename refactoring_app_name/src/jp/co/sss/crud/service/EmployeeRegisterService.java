package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;

/**
 * 新規登録用のサービスクラス
 */
public class EmployeeRegisterService {
	/**
	 * 新規社員の登録処理
	 * @param br
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertEmp(BufferedReader br)
			throws IOException, ParseException, SQLException, ClassNotFoundException {
		// 登録する値を入力
		System.out.print(ConstantMsg.INPUT_EMP_NAME);
		String empName = br.readLine();
		System.out.print(ConstantMsg.INPUT_GENDER);
		String gender = br.readLine();
		System.out.print(ConstantMsg.INPUT_BIRTH_DAY);
		String birthday = br.readLine();
		System.out.print(ConstantMsg.UPDATE_DEPT_ID);
		String inputDeptId = br.readLine();

		// 登録機能の呼出
		DBController.insert(empName, gender, birthday, inputDeptId);
	}
}
