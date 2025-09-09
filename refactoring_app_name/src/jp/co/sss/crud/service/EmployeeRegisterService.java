package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;
import static jp.co.sss.crud.util.ConstantValue.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;

/**
 * 新規登録用のサービスクラス
 */
public class EmployeeRegisterService implements IEmployeeService {

	/**
	 * 新規社員の登録処理
	 * @throws SystemErrorException 
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeNameReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 登録する値を入力
		System.out.print(INPUT_EMP_NAME);
		String empName = (String) reader.input();

		System.out.print(INPUT_GENDER);
		reader = new EmployeeGenderReader();
		int gender = (int) reader.input();

		System.out.print(INPUT_BIRTH_DAY);
		reader = new EmployeeBirthdayReader();
		String birthday = (String) reader.input();

		System.out.print(UPDATE_DEPT_ID);
		reader = new EmployeeDeptIdReader();
		int deptId = (int) reader.input();

		Employee employee = setFields(empName, gender, birthday, deptId);
		employeeDAO.insert(employee);

		ConsoleWriter.completeInsertMsg();
	}

	/**
	 * DTOに値をセット
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @return Employee Dto
	 */
	public Employee setFields(String empName, int gender, String birthday, int deptId) {
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
	 * PreparedStatementに値をバインド
	 * @param preparedStatement
	 * @param employee
	 * @throws SystemErrorException
	 */
	public void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		try {
			// 入力値をバインド
			preparedStatement.setString(INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			preparedStatement.setObject(INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(INDEX_FOUR, employee.getDepartment().getDeptId());
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException(MSG_SYSTEM_ERROR, e);
		}
	}
}
