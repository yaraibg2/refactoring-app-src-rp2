package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.util.ConstantMsg;

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
		EmployeeEmpIdReader employeeEmpIdReader = new EmployeeEmpIdReader();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// 削除する社員IDを入力
		System.out.print(ConstantMsg.INPUT_DELETE);

		String empId = (String) employeeEmpIdReader.input();
		Integer parsedEmpId = Integer.parseInt(empId);
		employeeDAO.delete(parsedEmpId);

		ConsoleWriter.completeDeleteMsg();
	}
}
