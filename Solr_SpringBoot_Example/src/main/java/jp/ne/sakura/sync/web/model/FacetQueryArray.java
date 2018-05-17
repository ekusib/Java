package jp.ne.sakura.sync.web.model;

public class FacetQueryArray {

	private Integer countInt;
	private String queryStr;
	private String pubStr;

	public FacetQueryArray(Integer countInt, String queryStr,String pubStr) {

		this.countInt = countInt;
		this.queryStr = queryStr;
		this.pubStr = pubStr;
	}

	public Integer getCountInt() {
		return countInt;
	}

	public void setCountInt(Integer countInt) {
		this.countInt = countInt;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getPubStr() {
		return pubStr;
	}

	public void setPubStr(String pubStr) {
		this.pubStr = pubStr;
	}

}
