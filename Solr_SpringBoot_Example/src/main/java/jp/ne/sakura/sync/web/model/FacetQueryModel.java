package jp.ne.sakura.sync.web.model;

import java.util.List;

public class FacetQueryModel {

	private String queryLabelName;
	private List<FacetQueryArray> facetCountLink;
	private String[][] queriesArray;

	/** コンストラクター */


	public FacetQueryModel(String queryLabelName, List<FacetQueryArray> facetCountLink, String[][] queriesArray) {

		this.queryLabelName = queryLabelName;
		this.facetCountLink = facetCountLink;
		this.queriesArray = queriesArray;
	}

	public String getQueryLabelName() {
		return queryLabelName;
	}

	public void setQueryLabelName(String queryLabelName) {
		this.queryLabelName = queryLabelName;
	}

	public List<FacetQueryArray> getFacetCountLink() {
		return facetCountLink;
	}

	public void setFacetCountLink(List<FacetQueryArray> facetCountLink) {
		this.facetCountLink = facetCountLink;
	}

	public String[][] getQueriesArray() {
		return queriesArray;
	}

	public void setQueriesArray(String[][] queriesArray) {
		this.queriesArray = queriesArray;
	}

}