package jp.ne.sakura.sync.web.model;

public class RightModel {

	/**
	 * url リンクURL
	 * title タイトル
	 * summary 概要
	 * author 著者
	 * info 定価 ページ数 isbn等の情報
	 */
	private String url;
	private String title;
	private String summary;
	private String author;
	private String info;


	/** コンストラクター */
	public RightModel(String url, String title, String summary, String author, String info){

		this.url = url;
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.info = info;

	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getSummary(){
		return summary;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getInfo(){
		return info;
	}

	public void setInfo(String info){
		this.info = info;
	}
}
