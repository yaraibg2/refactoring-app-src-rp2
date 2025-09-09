package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;
import static jp.co.sss.crud.util.ConstantValue.*;

import jp.co.sss.crud.db.EmployeeDAO;
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
import jp.co.sss.crud.util.Convert;

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

		if (isSuccess == FAILED) {
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

		System.out.print(INPUT_GENDER);
		reader = new EmployeeGenderReader();
		int gender = (int) reader.input();

		System.out.print(INPUT_BIRTH_DAY);
		reader = new EmployeeBirthdayReader();
		String birthday = (String) reader.input();

		System.out.print(UPDATE_DEPT_ID);
		reader = new EmployeeDeptIdReader();
		int deptId = (int) reader.input();

		Employee employee = Convert.setDtoFields(empName, gender, birthday, deptId, empId);

		return employee;
	}
}
