package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.io.IConsoleReader;

/**
 * 名前検索用のサービスクラス
 */
public class EmployeeFindByEmpNameService implements IEmployeeService {

	/**
	 * 社員を名前で検索
	 * @throws SystemErrorException
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeNameReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 社員名検索
		System.out.print(INPUT_EMP_NAME);
		// 検索ワード
		String name = (String) reader.input();

		List<Employee> employees = employeeDAO.findByEmployeeName(name);

		ConsoleWriter.showEmployees(employees);
	}
}
