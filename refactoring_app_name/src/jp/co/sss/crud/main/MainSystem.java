package jp.co.sss.crud.main;

import static jp.co.sss.crud.util.ConstantValue.*;

import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.IConsoleReader;
import jp.co.sss.crud.io.MenuNoReader;
import jp.co.sss.crud.service.IEmployeeService;

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
	 */
	public static void main(String[] args) {
		int menuNo = 0;
		IConsoleReader reader = new MenuNoReader();

		do {
			try {
				ConsoleWriter.showMenu();

				menuNo = (int) reader.input();
				IEmployeeService service = IEmployeeService.getInstanceByMenuNo(menuNo);
				// 機能の呼出
				if (service != null) {
					service.execute();
				}
			} catch (IllegalInputException e) {
				System.out.println(e.getMessage());
				System.out.println();
				continue;
			} catch (SystemErrorException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}
		} while (menuNo != SYSTEM_END_NUMBER);
		ConsoleWriter.systemEnd();
	}
}