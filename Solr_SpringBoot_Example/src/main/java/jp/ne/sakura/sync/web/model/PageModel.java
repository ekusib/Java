package jp.ne.sakura.sync.web.model;

import java.util.List;

public class PageModel {

	private List<Integer> pages;
	private int pcnt;
	private int cpag;

	/** コンストラクター */

	public PageModel(List<Integer> pages, int pcnt, int cpag) {

		this.pages = pages;
		this.pcnt = pcnt;
		this.cpag = cpag;

	}

	public List<Integer> getPages(){
		return pages;
	}

	public void setPages(List<Integer> pages){
		this.pages = pages;
	}

	public int getPcnt(){
		return pcnt;
	}

	public void setPcnt(int pcnt){
		this.pcnt = pcnt;
	}

	public int getCpag(){
		return cpag;
	}

	public void setCpag(int cpag){
		this.cpag = cpag;
	}
}
