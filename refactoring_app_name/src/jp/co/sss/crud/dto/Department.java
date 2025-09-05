package jp.co.sss.crud.dto;

/**
 * DepartmentテーブルのDtoクラス
 */
public class Department {
	/** 部署ID */
	private Integer deptId;
	/** 部署名 */
	private String deptName;

	/**
	 * 部署IDを取得
	 * @return deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * 部署IDをセット
	 * @param deptId
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * 部署名の取得
	 * @return deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 部署名のセット
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
