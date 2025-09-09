package jp.co.sss.crud.service;

import static jp.co.sss.crud.util.ConstantMsg.*;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.io.IConsoleReader;

/**
 * 削除用のサービスクラス
 */
public class EmployeeDeleteService implements IEmployeeService {

	/**
	 * 社員を削除
	 * @throws SystemErrorException
	 * @throws IllegalInputException 
	 */
	@Override
	public void execute() throws SystemErrorException, IllegalInputException {
		IConsoleReader reader = new EmployeeEmpIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 削除する社員IDを入力
		System.out.print(INPUT_DELETE);

		int empId = (int) reader.input();
		employeeDAO.delete(empId);

		ConsoleWriter.completeDeleteMsg();
	}
}
