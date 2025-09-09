package jp.co.sss.crud.util;

import static jp.co.sss.crud.util.ConstantMsg.*;
import static jp.co.sss.crud.util.ConstantValue.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;

/**
 * 共通処理を行うユーティリティクラス
 */
public class Convert {

	/**
	 * インスタンス化の禁止
	 */
	private Convert() {
	}

	/**
	 * ResultSetからレコードを抽出
	 * @param resultSet
	 * @return 社員リスト
	 * @throws SystemErrorException
	 */
	public static List<Employee> resultSetToEmployee(ResultSet resultSet) throws SystemErrorException {
		try {
			//resultSetがnull、もしくはその結果Setがない場合は空のリストを返す
			if (resultSet == null || !resultSet.isBeforeFirst()) {
				List<Employee> emptyEmployees = new ArrayList<>();
				return emptyEmployees;
			}

			List<Employee> employees = new ArrayList<>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				Department department = new Department();

				employee.setEmpId(resultSet.getInt(RECORD_EMP_ID));
				employee.setEmpName(resultSet.getString(RECORD_EMP_NAME));
				employee.setGender(resultSet.getInt(RECORD_GENDER));
				employee.setBirthday(resultSet.getString(RECORD_BIRTHDAY));
				department.setDeptName(resultSet.getString(RECORD_DEPT_NAME));
				employee.setDepartment(department);

				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			throw new SystemErrorException(MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * DTOに値をセット
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @return Employee Dto
	 */
	public static Employee setDtoFields(String empName, int gender, String birthday, int deptId) {
		Employee employee = new Employee();
		Department department = new Department();
		employee.setEmpName(empName);
		employee.setGender(gender);
		employee.setBirthday(birthday);
		department.setDeptId(deptId);
		employee.setDepartment(department);

		return employee;
	}

	/**
	 * DTOに入力値をセット
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @param empId
	 * @return Employee Dto
	 */
	public static Employee setDtoFields(String empName, int gender, String birthday, int deptId, int empId) {
		Employee employee = new Employee();
		Department department = new Department();
		employee.setEmpName(empName);
		employee.setGender(gender);
		employee.setBirthday(birthday);
		department.setDeptId(deptId);
		employee.setDepartment(department);
		employee.setEmpId(empId);

		return employee;
	}

	/**
	 * PreparedStatementに値をバインド
	 * @param preparedStatement
	 * @param employee
	 * @throws SystemErrorException
	 */
	public static void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		if (preparedStatement == null || employee == null) {
			return;
		}

		try {
			preparedStatement.setString(INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			preparedStatement.setObject(INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(INDEX_FOUR, employee.getDepartment().getDeptId());
			if (employee.getEmpId() != null) {
				preparedStatement.setInt(INDEX_FIVE, employee.getEmpId());
			}
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException(MSG_SYSTEM_ERROR, e);
		}
	}
}
