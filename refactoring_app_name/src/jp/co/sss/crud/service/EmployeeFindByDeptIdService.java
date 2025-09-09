package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.IConsoleReader;

/**
 * 主キー検索用のサービスクラス
 */
public class EmployeeFindByDeptIdService implements IEmployeeService {

	/**
	 * 社員をIDで検索
	 * @throws SystemErrorException
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeDeptIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 検索する部署IDを入力
		System.out.print(FIND_BY_DEPT_ID);
		int deptId = (int) reader.input();

		List<Employee> employees = employeeDAO.findByDeptId(deptId);

		ConsoleWriter.showEmployees(employees);
	}
}
