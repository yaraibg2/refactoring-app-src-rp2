package jp.co.sss.crud.db;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.service.EmployeeRegisterService;
import jp.co.sss.crud.service.EmployeeUpdateService;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 * @throws SystemErrorException 
	 */
	public static void findAll() throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.findAll();

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws SystemErrorException
	 */
	public static void findByName(String name) throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		List<Employee> employees = employeeDAO.findByEmployeeName(name);

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws SystemErrorException
	 * @param deptId 部署ID
	 */
	public static void findByDeptId(String deptId) throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		int parsedDeptId = Integer.parseInt(deptId);
		List<Employee> employees = employeeDAO.findByDeptId(parsedDeptId);

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws SystemErrorException
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeRegisterService employeeRegisterService = new EmployeeRegisterService();
		Employee employee = employeeRegisterService.setFields(empName, gender, birthday, deptId);
		employeeDAO.insert(employee);

		ConsoleWriter.completeInsertMsg();
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws SystemErrorException 
	 */
	public static void update(String empId)
			throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeUpdateService employeeUpdateService = new EmployeeUpdateService();
		Employee employee = employeeUpdateService.readLineAndsetField(empId);
		employeeDAO.update(employee);

		ConsoleWriter.completeUpdateMsg();
	}

	/**
	 * 社員情報を1件削除
	 * @throws SystemErrorException 
	 * @param empId 社員ID
	 */
	public static void delete(Integer empId) throws SystemErrorException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.delete(empId);

		ConsoleWriter.completeDeleteMsg();
	}
}
