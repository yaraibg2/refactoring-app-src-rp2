package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.util.ConstantMsg;

/**
 * 削除用のサービスクラス
 */
public class EmployeeDeleteService {
	/**
	 * インスタンス化の禁止
	 */
	private EmployeeDeleteService() {
	}

	/**
	 * 社員を削除
	 * @throws SystemErrorException 
	 */
	public static void deleteEmp(BufferedReader br) throws SystemErrorException {
		// 削除する社員IDを入力
		System.out.print(ConstantMsg.INPUT_DELETE);

		try {
			String empId = br.readLine();
			Integer parsedEmpId = Integer.parseInt(empId);
			// 削除機能の呼出
			DBController.delete(parsedEmpId);
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}
}
