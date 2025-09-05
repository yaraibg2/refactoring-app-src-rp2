package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

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
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAll() throws ClassNotFoundException, SQLException {

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NO_HIT_EMP);
				return;
			}

			// レコードを出力
			System.out.println(ConstantMsg.RECORD_HEAD);
			List<Employee> employees = new ArrayList<>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				Department department = new Department();

				employee.setEmpId(resultSet.getInt(ConstantMsg.RECORD_EMP_ID));
				employee.setEmpName(resultSet.getString(ConstantMsg.RECORD_EMP_NAME));
				employee.setGender(resultSet.getInt(ConstantMsg.RECORD_GENDER));
				employee.setBirthday(resultSet.getDate(ConstantMsg.RECORD_BIRTHDAY));

				department.setDeptName(resultSet.getString(ConstantMsg.RECORD_DEPT_ID));
				employee.setDepartment(department);

				employees.add(employee);
			}
			System.out.println();
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = br.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, "%" + searchWord + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NO_HIT_EMP);
				return;
			}

			System.out.println(ConstantMsg.RECORD_HEAD);
			while (resultSet.next()) {
				System.out.print(resultSet.getString(ConstantMsg.RECORD_EMP_ID));
				System.out.print(ConstantMsg.SPACE);

				System.out.print(resultSet.getString(ConstantMsg.RECORD_EMP_NAME));
				System.out.print(ConstantMsg.SPACE);

				String genderString = resultSet.getString(ConstantMsg.RECORD_GENDER);
				int gender = Integer.parseInt(genderString);
				if (gender == ConstantValue.NO_ANSWER_NUMBER) {
					System.out.print(ConstantMsg.NO_ANSWER);
				} else if (gender == ConstantValue.GENDER_WOMAN_NUMBER) {
					System.out.print(ConstantMsg.GENDER_MAN);

				} else if (gender == ConstantValue.GENDER_WOMAN_NUMBER) {
					System.out.print(ConstantMsg.GENDER_WOMAN);

				} else if (gender == ConstantValue.GENDER_OTHER_NUMBER) {
					System.out.print(ConstantMsg.GENDER_OTHER);

				}

				System.out.print(ConstantMsg.SPACE);
				System.out.print(resultSet.getString(ConstantMsg.RECORD_BIRTHDAY));
				System.out.print(ConstantMsg.SPACE);

				System.out.println(resultSet.getString(ConstantMsg.RECORD_DEPT_NAME));
			}

			System.out.println("");

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(ConstantValue.INDEX_ONE, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NO_HIT_EMP);
				return;
			}

			System.out.println(ConstantMsg.RECORD_HEAD);
			while (resultSet.next()) {
				System.out.print(resultSet.getString(ConstantMsg.RECORD_EMP_ID));
				System.out.print(ConstantMsg.SPACE);

				System.out.print(resultSet.getString(ConstantMsg.RECORD_EMP_NAME));
				System.out.print(ConstantMsg.SPACE);

				String genderString = resultSet.getString(ConstantMsg.RECORD_GENDER);
				int gender = Integer.parseInt(genderString);
				if (gender == ConstantValue.NO_ANSWER_NUMBER) {
					System.out.print(ConstantMsg.NO_ANSWER);
				} else if (gender == ConstantValue.GENDER_MAN_NUMBER) {
					System.out.print(ConstantMsg.GENDER_MAN);

				} else if (gender == ConstantValue.GENDER_WOMAN_NUMBER) {
					System.out.print(ConstantMsg.GENDER_WOMAN);

				} else if (gender == ConstantValue.GENDER_OTHER_NUMBER) {
					System.out.print(ConstantMsg.GENDER_OTHER);

				}

				System.out.print(ConstantMsg.SPACE);
				System.out.print(resultSet.getString(ConstantMsg.RECORD_BIRTHDAY));
				System.out.print(ConstantMsg.SPACE);

				String deptIdString = resultSet.getString(ConstantMsg.RECORD_DEPT_ID);
				int parsedDeptId = Integer.parseInt(deptIdString);
				if (parsedDeptId == ConstantValue.DEPT_ID_ONE) {
					System.out.println(ConstantMsg.DEPT_SALES);
				} else if (parsedDeptId == ConstantValue.DEPT_ID_TWO) {
					System.out.println(ConstantMsg.DEPT_ACCOUNTING);
				} else if (parsedDeptId == ConstantValue.DEPT_ID_THREE) {
					System.out.println(ConstantMsg.DEPT_GENERAL);

				}
			}

			System.out.println("");
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, empName);
			preparedStatement.setInt(ConstantValue.INDEX_TWO, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantMsg.DATE_FORMAT);
			preparedStatement.setObject(ConstantValue.INDEX_THREE, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(ConstantValue.INDEX_FOUR, Integer.parseInt(deptId));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(ConstantMsg.COMPLETE_INSERT);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void update(String empId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.INPUT_EMP_NAME);
			String empName = br.readLine();
			// 性別を入力
			System.out.print(ConstantMsg.INPUT_GENDER);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(ConstantMsg.INPUT_BIRTH_DAY);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.UPDATE_DEPT_ID);
			String deptId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(ConstantValue.INDEX_ONE, empName);
			preparedStatement.setInt(ConstantValue.INDEX_TWO, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantMsg.DATE_FORMAT);
			preparedStatement.setObject(ConstantValue.INDEX_THREE, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(ConstantValue.INDEX_FOUR, Integer.parseInt(deptId));
			preparedStatement.setInt(ConstantValue.INDEX_FIVE, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String empId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.COMPLETE_DELETE);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}
