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
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;

/**
 * 更新用のサービスクラス
 */
public class EmployeeUpdateService implements IEmployeeService {

	/**
	 * 社員情報の更新
	 * @throws SystemErrorException
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeEmpIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();

		// 更新する社員IDを入力
		System.out.print(INPUT_UPDATE);

		// 更新する値を入力する
		int empId = (int) reader.input();

		Employee employee = readLineAndsetField(empId);
		Integer isSuccess = employeeDAO.update(employee);

		if (isSuccess == 0) {
			ConsoleWriter.errorUpdateMsg();
		} else {
			ConsoleWriter.completeUpdateMsg();
		}
	}

	/**
	 * 入力値を受け取り、DTOに移して返す
	 * @param empId
	 * @return Employee Dto
	 * @throws SystemErrorException
	 * @throws IllegalInputException 
	 */
	public Employee readLineAndsetField(int empId) throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeNameReader();

		System.out.print(INPUT_EMP_NAME);
		String empName = (String) reader.input();
		// 性別を入力
		System.out.print(INPUT_GENDER);
		reader = new EmployeeGenderReader();
		int gender = (int) reader.input();
		// 誕生日を入力
		System.out.print(INPUT_BIRTH_DAY);
		reader = new EmployeeBirthdayReader();
		String birthday = (String) reader.input();

		// 部署IDを入力
		System.out.print(UPDATE_DEPT_ID);
		reader = new EmployeeDeptIdReader();
		int deptId = (int) reader.input();

		Employee employee = setDtoFields(empName, gender, birthday, deptId, empId);

		return employee;
	}

	/**
	 * DTOに入力値をセットする
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param deptId
	 * @param empId
	 * @return Employee Dto
	 */
	private static Employee setDtoFields(String empName, int gender, String birthday, int deptId, int empId) {
		Employee employee = new Employee();
		Department department = new Department();
		// 入力値をバインド	
		employee.setEmpName(empName);
		employee.setGender(gender);
		employee.setBirthday(birthday);
		department.setDeptId(deptId);
		employee.setDepartment(department);
		employee.setEmpId(empId);

		return employee;
	}

	/**
	 * PreparedStatementに入力値をバインド
	 * @param preparedStatement
	 * @param employee
	 * @throws SystemErrorException
	 */
	public void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		try {
			preparedStatement.setString(INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			preparedStatement.setObject(INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(INDEX_FOUR, employee.getDepartment().getDeptId());
			preparedStatement.setInt(INDEX_FIVE, employee.getEmpId());
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException(MSG_SYSTEM_ERROR, e);
		}
	}
}
