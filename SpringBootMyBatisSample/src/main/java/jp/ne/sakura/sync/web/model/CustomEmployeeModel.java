package jp.ne.sakura.sync.web.model;

import java.io.Serializable;

public class CustomEmployeeModel implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 名前 **/
	private String name;

	/** メールアドレス **/
	private String mail;

	/** 役職番号 **/
	private Integer positionNo;

	/** 役職名 **/
	private String positionName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(Integer positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}

