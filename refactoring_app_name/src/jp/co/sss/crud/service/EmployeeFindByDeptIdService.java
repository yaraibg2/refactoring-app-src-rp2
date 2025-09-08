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
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.util.ConstantMsg;

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
		EmployeeDeptIdReader employeeDeptIdReader = new EmployeeDeptIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 検索する部署IDを入力
		System.out.print(ConstantMsg.FIND_BY_DEPT_ID);
		String deptId = (String) employeeDeptIdReader.input();

		int parsedDeptId = Integer.parseInt(deptId);
		List<Employee> employees = employeeDAO.findByDeptId(parsedDeptId);

		ConsoleWriter.showEmployees(employees);
	}

	/**
	 * 部署IDで絞り込んだレコードを抽出
	 * @param resultSet
	 * @return
	 * @throws SystemErrorException
	 */
	public List<Employee> getRecordFindByDeptId(ResultSet resultSet) throws SystemErrorException {
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
