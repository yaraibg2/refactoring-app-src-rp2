package jp.co.sss.crud.dto;

import jp.co.sss.crud.util.ConstantMsg;

/**
 * EmployeeテーブルのDtoクラス
 */
public class Employee {
	/** 社員ID */
	private Integer empId;
	/** 社員名 */
	private String empName;
	/** 性別 */
	private Integer gender;
	/** 生年月日 */
	private String birthday;
	/** 部署ID */
	private Department department;

	@Override
	public String toString() {
		String genderJapanese = "";
		if (this.gender == 1) {
			genderJapanese = ConstantMsg.GENDER_MAN;
		} else if (this.gender == 2) {
			genderJapanese = ConstantMsg.GENDER_WOMAN;
		}

		return empId + ConstantMsg.SPACE + empName + ConstantMsg.SPACE
				+ genderJapanese + ConstantMsg.SPACE + birthday + ConstantMsg.SPACE
				+ department.getDeptName();
	}

	/**
	 * 社員IDを取得
	 * @return empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * 社員IDをセット
	 * @param empId
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * 社員名を取得
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 社員名をセット
	 * @param empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * 性別を取得
	 * @return gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 性別をセット
	 * @param gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 生年月日を取得
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 生年月日をセット
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 部署を取得
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * 部署をセット
	 * @param department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}
