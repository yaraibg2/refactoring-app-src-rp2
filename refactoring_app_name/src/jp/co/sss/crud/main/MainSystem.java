package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.service.EmployeeAllFindService;
import jp.co.sss.crud.service.EmployeeDeleteService;
import jp.co.sss.crud.service.EmployeeFindByDeptIdService;
import jp.co.sss.crud.service.EmployeeFindByEmpNameService;
import jp.co.sss.crud.service.EmployeeRegisterService;
import jp.co.sss.crud.service.EmployeeUpdateService;
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
	 * @throws SystemErrorException 
	 */
	public static void main(String[] args) throws SystemErrorException {
		int menuNumber = 0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			do {
				// メニューの表示
				ConsoleWriter.showMenu();
				// メニュー番号の入力
				String menuNoStr = br.readLine();
				menuNumber = Integer.parseInt(menuNoStr);
				// 機能の呼出
				switch (menuNumber) {
				case ConstantValue.MENU_SELECT_ALL:
					EmployeeAllFindService.findAll();
					break;

				case ConstantValue.MENU_SEARCH_EMP_NAME:
					EmployeeFindByEmpNameService.findByName(br);
					break;

				case ConstantValue.MENU_SEARCH_DEPT_ID:
					EmployeeFindByDeptIdService.findByDeptId(br);
					break;

				case ConstantValue.MENU_INSERT:
					EmployeeRegisterService.insertEmp(br);
					break;

				case ConstantValue.MENU_UPDATE:
					EmployeeUpdateService.updateEmp(br);
					break;

				case ConstantValue.MENU_DELETE:
					EmployeeDeleteService.deleteEmp(br);
					break;
				}
			} while (menuNumber != ConstantValue.SYSTEM_END_NUMBER);
			ConsoleWriter.systemEnd();
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}
}
