package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.util.Convert;

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

		Employee employee = Convert.setDtoFields(empName, gender, birthday, deptId);
		employeeDAO.insert(employee);

		ConsoleWriter.completeInsertMsg();
	}
}
