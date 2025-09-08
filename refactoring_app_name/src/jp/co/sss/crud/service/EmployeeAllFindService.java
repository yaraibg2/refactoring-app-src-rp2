package jp.co.sss.crud.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.util.ConstantMsg;

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
		// 全件表示機能の呼出
		DBController.findAll();
	}

	/**
	 * 全レコードが入ったリストを抽出
	 * @param resultSet
	 * @return
	 * @throws SystemErrorException
	 */
	public List<Employee> getAllRecord(ResultSet resultSet) throws SystemErrorException {

		try {
			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				ConsoleWriter.showNonExistTarget();
				return null;
			}

			// レコードを出力
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
