package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;

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

/**
 * 全件検索用のサービスクラス
 */
public class EmployeeAllFindService implements IEmployeeService {

	/**
	 * 全件検索
	 * @throws SystemErrorException 
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.findAll();

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 全レコードが入ったリストを抽出
	 * @param resultSet
	 * @return 全社員リスト
	 * @throws SystemErrorException
	 */
	public List<Employee> getAllRecord(ResultSet resultSet) throws SystemErrorException {

		try {
			//resultSetの結果Setがない場合はnullを返す
			if (!resultSet.isBeforeFirst()) {
				ConsoleWriter.showNonExistTarget();
				return null;
			}

			// レコードを出力
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
}
