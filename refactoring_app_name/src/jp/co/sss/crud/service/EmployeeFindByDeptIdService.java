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

	/**
	 * 部署IDで絞り込んだレコードを抽出
	 * @param resultSet
	 * @return 社員リスト
	 * @throws SystemErrorException
	 */
	public List<Employee> getRecordFindByDeptId(ResultSet resultSet) throws SystemErrorException {
		try {
			//resultSetの結果Setがない場合はnullを返す
			if (!resultSet.isBeforeFirst()) {
				return null;
			}

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
