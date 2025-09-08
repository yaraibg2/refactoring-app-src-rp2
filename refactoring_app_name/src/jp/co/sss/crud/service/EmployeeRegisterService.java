package jp.co.sss.crud.service;

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
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

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
		EmployeeNameReader employeeNameReader = new EmployeeNameReader();
		EmployeeGenderReader employeeGenderReader = new EmployeeGenderReader();
		EmployeeBirthdayReader employeeBirthdayReader = new EmployeeBirthdayReader();
		EmployeeDeptIdReader employeeDeptIdReader = new EmployeeDeptIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();

		// 登録する値を入力
		System.out.print(ConstantMsg.INPUT_EMP_NAME);
		String empName = (String) employeeNameReader.input();
		System.out.print(ConstantMsg.INPUT_GENDER);
		String gender = (String) employeeGenderReader.input();
		System.out.print(ConstantMsg.INPUT_BIRTH_DAY);
		String birthday = (String) employeeBirthdayReader.input();
		System.out.print(ConstantMsg.UPDATE_DEPT_ID);
		String deptId = (String) employeeDeptIdReader.input();

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
	public Employee setFields(String empName, String gender, String birthday, String deptId) {
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
	 * @throws SystemErrorException
	 */
	public void bindPreparedStatement(PreparedStatement preparedStatement, Employee employee)
			throws SystemErrorException {
		try {
			// 入力値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, employee.getEmpName());
			preparedStatement.setInt(ConstantValue.INDEX_TWO, employee.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantMsg.DATE_FORMAT);
			preparedStatement.setObject(ConstantValue.INDEX_THREE, sdf.parse(employee.getBirthday()), Types.DATE);
			preparedStatement.setInt(ConstantValue.INDEX_FOUR, employee.getDepartment().getDeptId());
		} catch (SQLException | ParseException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}
}
