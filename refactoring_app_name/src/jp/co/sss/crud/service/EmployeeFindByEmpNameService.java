package jp.co.sss.crud.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeNameReader;
import jp.co.sss.crud.util.ConstantMsg;

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
		EmployeeNameReader employeeNameReader = new EmployeeNameReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 社員名検索
		System.out.print(ConstantMsg.INPUT_EMP_NAME);
		// 検索ワード
		String name = (String) employeeNameReader.input();

		List<Employee> employees = employeeDAO.findByEmployeeName(name);

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 社員名で絞り込んだレコードを抽出
	 * @param resultSet
	 * @return
	 * @throws SystemErrorException
	 */
	public List<Employee> getRecordFindByName(ResultSet resultSet) throws SystemErrorException {
		try {
			if (!resultSet.isBeforeFirst()) {
				ConsoleWriter.showNonExistTarget();
				return null;
			}

			List<Employee> employees = new ArrayList<>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				Department department = new Department();

				employee.setEmpId(resultSet.getInt(ConstantMsg.RECORD_EMP_ID));
				employee.setEmpName(resultSet.getString(ConstantMsg.RECORD_EMP_NAME));
				employee.setGender(resultSet.getInt(ConstantMsg.RECORD_GENDER));
				employee.setBirthday(resultSet.getString(ConstantMsg.RECORD_BIRTHDAY));
				department.setDeptName(resultSet.getString(ConstantMsg.RECORD_DEPT_NAME));
				employee.setDepartment(department);

				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}
}
