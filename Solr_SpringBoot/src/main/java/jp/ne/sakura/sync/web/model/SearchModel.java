package jp.ne.sakura.sync.web.model;


public class SearchModel{

	private String query;

	private String[] fq;

	private int start;


	public String getQuery(){
		return query;
	}

	public String[] getFq(){
		return fq;
	}

	public int getStart(){
		return start;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public void setFq(String[] fq){
		this.fq = fq;
	}

	public void setStart(int start){
		this.start = start;
	}

}
