package jp.ne.sakura.sync.web.model;

public class FacetFieldArray {

	private String facetName;
	private String nameStr;
	private Long countLong;

	/** コンストラクター */
	public FacetFieldArray(String facetName, String nameStr, Long countLong){

		this.facetName = facetName;
		this.nameStr = nameStr;
		this.countLong = countLong;
	}

	public String getFacetName(){
		return facetName;
	}

	public void setFacetName(String facetName){
		this.facetName = facetName;
	}

	public String getNameStr(){
		return nameStr;
	}

	public void setNameStr(String nameStr){
		this.nameStr = nameStr;
	}

	public Long getCountLong(){
		return countLong;
	}

	public void setCountLong(Long countLong){
		this.countLong = countLong;
	}


}
