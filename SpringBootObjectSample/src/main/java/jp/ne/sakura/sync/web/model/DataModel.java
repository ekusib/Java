package jp.ne.sakura.sync.web.model;

public class DataModel {

	// id番号
	private int id;

	// 名前
	private String name;

	// メールアドレス
	private String mail;

	// コンストラクター
	public DataModel(int id, String name, String mail){

		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}
