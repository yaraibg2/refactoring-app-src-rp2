package jp.co.sss.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.service.EmployeeAllFindService;
import jp.co.sss.crud.service.EmployeeFindByDeptIdService;
import jp.co.sss.crud.service.EmployeeFindByEmpNameService;
import jp.co.sss.crud.service.EmployeeRegisterService;
import jp.co.sss.crud.service.EmployeeUpdateService;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

public class EmployeeDAO implements IEmployeeDAO {

	/**
	 *全件検索のリストを返す
	 */
	@Override
	public List<Employee> findAll() throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			EmployeeAllFindService employeeAllFindService = new EmployeeAllFindService();

			return employeeAllFindService.getAllRecord(resultSet);

		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * 社員名で検索したリストを返す
	 * @param searchName 検索名
	 */
	@Override
	public List<Employee> findByEmployeeName(String searchName) throws SystemErrorException {
		EmployeeFindByEmpNameService employeeFindByEmpNameService = new EmployeeFindByEmpNameService();

		try {
			Connection connection = DBManager.getConnection();
			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, "%" + searchName + "%");
			ResultSet resultSet = preparedStatement.executeQuery();

			try (connection; preparedStatement; resultSet) {

				return employeeFindByEmpNameService.getRecordFindByName(resultSet);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * 部署IDで検索したリストを返す
	 * @param deptId 部署ID
	 */
	@Override
	public List<Employee> findByDeptId(int deptId) throws SystemErrorException {
		EmployeeFindByDeptIdService employeeFindByDeptIdService = new EmployeeFindByDeptIdService();

		try {
			Connection connection = DBManager.getConnection();
			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(ConstantValue.INDEX_ONE, deptId);
			ResultSet resultSet = preparedStatement.executeQuery();

			try (connection; preparedStatement; resultSet) {

				return employeeFindByDeptIdService.getRecordFindByDeptId(resultSet);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * 入力した社員情報をデータベースに登録
	 * @param employee
	 */
	@Override
	public void insert(Employee employee) throws SystemErrorException {
		EmployeeRegisterService employeeRegisterService = new EmployeeRegisterService();

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT)) {
			employeeRegisterService.bindPreparedStatement(preparedStatement, employee);
			// SQL文を実行
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * 入力した社員情報でデータベースを更新
	 * @param employee
	 */
	@Override
	public Integer update(Employee employee) throws SystemErrorException {
		EmployeeUpdateService employeeUpdateService = new EmployeeUpdateService();

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE)) {
			employeeUpdateService.bindPreparedStatement(preparedStatement, employee);
			// SQL文の実行(失敗時は戻り値0)
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

	/**
	 * 入力した社員IDの社員をデータベースから削除
	 * @param empId 社員ID
	 */
	@Override
	public Integer delete(Integer empId) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);) {
			// 社員IDをバインド
			preparedStatement.setInt(1, empId);
			// SQL文の実行(失敗時は戻り値0)
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.MSG_SYSTEM_ERROR, e);
		}
	}

}
