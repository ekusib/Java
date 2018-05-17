package jp.ne.sakura.sync.web.model;

import java.util.List;

public class SolrSpringBootModel {

	private String query;
	private String[] fq;
	private DataModel data;
	private List<RightModel> results;
	private List<FacetFieldModel> facet_fields;
	private List<FacetQueryModel> facet_queries;
	private PageModel pagination;


	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getFq() {
		return fq;
	}

	public void setFq(String[] fq) {
		this.fq = fq;
	}

	public DataModel getData() {
		return data;
	}

	public void setData(DataModel data) {
		this.data = data;
	}

	public List<RightModel> getResults() {
		return results;
	}

	public void setResults(List<RightModel> results) {
		this.results = results;
	}

	public List<FacetFieldModel> getFacet_fields() {
		return facet_fields;
	}

	public void setFacet_fields(List<FacetFieldModel> facet_fields) {
		this.facet_fields = facet_fields;
	}

	public List<FacetQueryModel> getFacet_queries() {
		return facet_queries;
	}

	public void setFacet_queries(List<FacetQueryModel> facet_queries) {
		this.facet_queries = facet_queries;
	}

	public PageModel getPagination() {
		return pagination;
	}

	public void setPagination(PageModel pagination) {
		this.pagination = pagination;
	}

}
