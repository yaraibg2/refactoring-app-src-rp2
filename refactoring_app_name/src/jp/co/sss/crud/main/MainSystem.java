package jp.co.sss.crud.main;

import jp.co.sss.crud.exception.IllegalInputException;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.MenuNoReader;
import jp.co.sss.crud.service.IEmployeeService;
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
	public static void main(String[] args) {
		int menuNo = 0;
		MenuNoReader menuReader = new MenuNoReader();

		do {
			try {
				// メニューの表示
				ConsoleWriter.showMenu();
				// メニュー番号の入力
				String inputMenuNo = (String) menuReader.input();
				menuNo = Integer.parseInt(inputMenuNo);
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
		} while (menuNo != ConstantValue.SYSTEM_END_NUMBER);
		ConsoleWriter.systemEnd();
	}
}