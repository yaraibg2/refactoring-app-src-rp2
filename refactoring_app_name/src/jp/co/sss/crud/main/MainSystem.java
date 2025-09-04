package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.service.EmployeeAllFindService;
import jp.co.sss.crud.service.EmployeeDeleteService;
import jp.co.sss.crud.service.EmployeeFindByDeptIdService;
import jp.co.sss.crud.service.EmployeeFindByEmpNameService;
import jp.co.sss.crud.service.EmployeeRegisterService;
import jp.co.sss.crud.service.EmployeeService;
import jp.co.sss.crud.service.EmployeeUpdateService;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		int menuNumber = 0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			do {
				// メニューの表示
				EmployeeService.showMenu();
				// メニュー番号の入力
				String menuNoStr = br.readLine();
				menuNumber = Integer.parseInt(menuNoStr);
				// 機能の呼出
				switch (menuNumber) {
				case 1:
					EmployeeAllFindService.findAll();
					break;

				case 2:
					EmployeeFindByEmpNameService.findByName();
					break;

				case 3:
					EmployeeFindByDeptIdService.findByDeptId(br);
					break;

				case 4:
					EmployeeRegisterService.insertEmp(br);
					break;

				case 5:
					EmployeeUpdateService.updateEmp(br);
					break;

				case 6:
					EmployeeDeleteService.deleteEmp();
					break;
				}
			} while (menuNumber != ConstantValue.SYSTEM_END_NUMBER);
			System.out.println(ConstantMsg.SYSTEM_END);
		}
	}
}
