package jp.co.sss.crud.util;

/**
 * 定数クラス
 */
public class Constants {

	/**
	 * インスタンス化を禁止
	 */
	private Constants() {
	}

	/** システム名 */
	public static final String MENU_SYSTEM_NAME = "=== 社員管理システム ===";

	/** 全件検索 */
	public static final String MENU_FIND_ALL = "1.全件表示";

	/** 社員名検索 */
	public static final String MENU_FIND_BY_NAME = "2.社員名検索";

	/** 部署ID検索 */
	public static final String MENU_FIND_BY_DEPT_ID = "3.部署ID検索";

	/** 新規登録 */
	public static final String MENU_INSERT_EMP = "4.新規登録";

	/** 更新 */
	public static final String MENU_UPDATE_EMP = "5.更新";

	/** 削除 */
	public static final String MENU_DELETE_EMP = "6.削除";

	/** 終了 */
	public static final String MENU_END = "7.終了";

	/** メニュー番号入力 */
	public static final String INPUT_MENU_NUMBER = "メニュー番号を入力してください：";

	/** 社員名入力 */
	public static final String INPUT_EMP_NAME = "社員名:";

	/** 検索する部署IDを入力 */
	public static final String FIND_BY_DEPT_ID = "部署ID(1:営業部、2:経理部、3:総務部)を入力してください:";

	/** 更新する部署IDを入力 */
	public static final String UPDATE_DEPT_ID = "部署ID(1:営業部、2:経理部、3:総務部):";

	/** 性別入力 */
	public static final String INPUT_GENDER = "性別(0:その他, 1:男性, 2:女性, 9:回答なし):";

	/** 生年月日入力 */
	public static final String INPUT_BIRTH_DAY = "生年月日(西暦年/月/日):";

	/** 更新入力 */
	public static final String INPUT_UPDATE = "更新する社員の社員IDを入力してください：";

	/** 更新完了 */
	public static final String COMPLETE_UPDATE = "社員情報を更新しました";

	/** 削除入力 */
	public static final String INPUT_DELETE = "削除する社員の社員IDを入力してください：";

	/** システム終了 */
	public static final String SYSTEM_END = "システムを終了します。";

	/** システム終了の番号 */
	public static final int SYSTEM_END_NUMBER = 7;
}
