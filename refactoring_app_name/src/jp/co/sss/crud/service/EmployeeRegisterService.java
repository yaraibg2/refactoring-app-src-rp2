package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 新規登録用のサービスクラス
 */
public class EmployeeRegisterService {
	/**
	 * インスタンス化の禁止
	 */
	private EmployeeRegisterService() {
	}

	/**
	 * 新規社員の登録処理
	 * @param br
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertEmp(BufferedReader br)
			throws SystemErrorException {
		try {
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
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}

	/**
	 * DTOに値をセット
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @return
	 */
	public static Employee setFields(String empName, String gender, String birthday, String deptId) {
		Employee employee = new Employee();
		Department department = new Department();
		employee.setEmpName(empName);
		employee.setGender(Integer.parseInt(gender));
		employee.setBirthday(birthday);
		department.setDeptId(Integer.parseInt(deptId));
		employee.setDepartment(department);

		return employee;
	}

	/**
	 * PreparedStatementに値をバインド
	 * @param preparedStatement
	 * @param employee
	 * @return
	 * @throws SystemErrorException
	 */
	public static void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		try {
			// 入力値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(ConstantValue.INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantMsg.DATE_FORMAT);
			preparedStatement.setObject(ConstantValue.INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(ConstantValue.INDEX_FOUR, employee.getDepartment().getDeptId());
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException();
		}
	}
}
