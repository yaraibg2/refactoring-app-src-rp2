package jp.co.sss.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * 更新用のサービスクラス
 */
public class EmployeeUpdateService {
	/**
	 * インスタンス化の禁止
	 */
	private EmployeeUpdateService() {
	}

	/**
	 * 社員情報の更新
	 * @param br
	 * @throws SystemErrorException
	 */
	public static void updateEmp(BufferedReader br)
			throws SystemErrorException {
		try {
			// 更新する社員IDを入力
			System.out.print(ConstantMsg.INPUT_UPDATE);

			// 更新する値を入力する
			String inputEmpId = br.readLine();
			Integer.parseInt(inputEmpId);

			// 更新機能の呼出
			DBController.update(inputEmpId);
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}

	/**
	 * 入力値を受け取り、DTOに移して返す
	 * @param empId
	 * @return
	 * @throws SystemErrorException
	 */
	public static Employee readLineAndsetField(String empId) throws SystemErrorException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print(ConstantMsg.INPUT_EMP_NAME);
			String empName = br.readLine();
			// 性別を入力
			System.out.print(ConstantMsg.INPUT_GENDER);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(ConstantMsg.INPUT_BIRTH_DAY);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.UPDATE_DEPT_ID);
			String deptId = br.readLine();

			Employee employee = setDtoFields(empName, gender, birthday, deptId, empId);

			return employee;
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}

	/**
	 * DTOに入力値をセットする
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @param empId
	 * @return
	 */
	private static Employee setDtoFields(String empName, String gender, String birthday, String deptId, String empId) {
		Employee employee = new Employee();
		Department department = new Department();
		// 入力値をバインド	
		employee.setEmpName(empName);
		employee.setGender(Integer.parseInt(gender));
		employee.setBirthday(birthday);
		department.setDeptId(Integer.parseInt(deptId));
		employee.setDepartment(department);
		employee.setEmpId(Integer.parseInt(empId));

		return employee;
	}

	/**
	 * PreparedStatementに入力値をバインド
	 * @param preparedStatement
	 * @param employee
	 * @throws SystemErrorException
	 */
	public static void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		try {
			preparedStatement.setString(ConstantValue.INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(ConstantValue.INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantMsg.DATE_FORMAT);
			preparedStatement.setObject(ConstantValue.INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(ConstantValue.INDEX_FOUR, employee.getDepartment().getDeptId());
			preparedStatement.setInt(ConstantValue.INDEX_FIVE, employee.getEmpId());
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException();
		}
	}
}
