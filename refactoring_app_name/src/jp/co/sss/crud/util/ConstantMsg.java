package jp.co.sss.crud.util;

/**
 * メッセージ用の定数クラス
 */
public class ConstantMsg {

	/**
	 * インスタンス化を禁止
	 */
	private ConstantMsg() {
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

	/** 該当者なし */
	public static final String NO_HIT_EMP = "該当者はいませんでした";

	/** Dateフォーマット */
	public static final String DATE_FORMAT = "yyyy/MM/dd";

	/** 登録完了メッセージ */
	public static final String COMPLETE_INSERT = "社員情報を登録しました";

	/** 削除完了メッセージ */
	public static final String COMPLETE_DELETE = "社員情報を削除しました";

	/** レコード出力 */
	public static final String RECORD_HEAD = "社員ID\t社員名\t性別\t生年月日\t部署名";

	/** db用emp_idレコード */
	public static final String RECORD_EMP_ID = "emp_id";

	/** db用emp_nameレコード */
	public static final String RECORD_EMP_NAME = "emp_name";

	/** db用genderレコード */
	public static final String RECORD_GENDER = "gender";

	/** db用birthdayレコード */
	public static final String RECORD_BIRTHDAY = "birthday";

	/** db用dept_idレコード */
	public static final String RECORD_DEPT_ID = "dept_id";

	/** db用dept_nameレコード */
	public static final String RECORD_DEPT_NAME = "dept_name";

	/** 回答なし */
	public static final String NO_ANSWER = "回答なし";

	/** 男性 */
	public static final String GENDER_MAN = "男性";

	/** 女性 */
	public static final String GENDER_WOMAN = "女性";

	/** その他 */
	public static final String GENDER_OTHER = "その他";

	/** 営業部 */
	public static final String DEPT_SALES = "営業部";

	/** 経理部 */
	public static final String DEPT_ACCOUNTING = "経理部";

	/** 総務部 */
	public static final String DEPT_GENERAL = "総務部";

	/** システムエラーメッセージ */
	public static final String MSG_SYSTEM_ERROR = "予期せぬエラーが発生しました。";

	/** 生年月日のバリデーションメッセージ */
	public static final String DATE_VALID_MSG = "正しい形式(西暦年/月/日)で日付を入力してください";

	/** 部署IDのバリデーションメッセージ */
	public static final String DEPT_ID_VALID_MSG = "1以上3以下の整数を入力してください";

	/** 部署IDの正規表現 */
	public static final String DEPT_ID_VALID_VALUE = "^[1-3１-３]{1}$";

	/** 社員IDのバリデーションメッセージ */
	public static final String EMP_ID_VALID_MSG = "1以上9999以下の整数を入力してください";

	/** 社員IDの正規表現 */
	public static final String EMP_ID_VALID_VALUE = "^[1-9１-９]{1}[0-9１-９]{0,3}$";

	/** 性別のバリデーションメッセージ */
	public static final String GENDER_VALID_MSG = "0,1,2,または9の整数を入力してください";

	/** 性別の正規表現 */
	public static final String GENDER_VALID_VALUE = "^|[0129０１２９]{1}$";

	/** 社員名のバリデーションメッセージ */
	public static final String EMP_NAME_VALID_MSG = "1文字以上30文字以下の文字列を入力してください";

	/** メニュー番号のバリデーションメッセージ */
	public static final String MENU_NO_VALID_MSG = "1以上7以下の整数を入力してください";

	/** メニュー番号の正規表現 */
	public static final String MENU_NO_VALID_VALUE = "^[1-7 １-７]$";

	/** 更新処理失敗時のメッセージ */
	public static final String UPDATE_ERROR = "更新処理に失敗しました。";

	/** 削除処理失敗時のメッセージ */
	public static final String DELETE_ERROR = "削除処理に失敗しました。";
}
