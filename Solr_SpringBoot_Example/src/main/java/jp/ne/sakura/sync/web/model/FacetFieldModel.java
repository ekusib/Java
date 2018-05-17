package jp.ne.sakura.sync.web.model;

import java.util.List;

public class FacetFieldModel {

	private String facetLabelName;
	private List<FacetFieldArray> facetFieldArray;

	/** コンストラクター */
	public FacetFieldModel(String facetLabelName, List<FacetFieldArray> facetFieldArray){

		this.facetLabelName = facetLabelName;
		this.facetFieldArray = facetFieldArray;
	}

	public String getFacetLabelName(){
		return facetLabelName;
	}

	public void setFacetLabelName(String facetLabelName){
		this.facetLabelName = facetLabelName;
	}

	public List<FacetFieldArray> getFacetFieldArray(){
		return facetFieldArray;
	}

	public void setFacetFieldArray(List<FacetFieldArray> facetFieldArray){
		this.facetFieldArray = facetFieldArray;
	}


}
