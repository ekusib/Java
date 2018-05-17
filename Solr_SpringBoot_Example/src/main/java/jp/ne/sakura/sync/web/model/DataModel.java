package jp.ne.sakura.sync.web.model;

public class DataModel {

	/**
	 * found 検索件数
	 * start 検索開始位置
	 * rows 表示件数
	 * qtime 検索時間
	 */
	private long found;
	private long start;
	private int rows = 10;
	private int qtime;

	/** コンストラクター */
	public DataModel(long found, long start, int rows, int qtime){

		this.found = found;
		this.start = start;
		this.rows = rows;
		this.qtime = qtime;

	}

	public long getFound(){
		return found;
	}

	public void setFound(long found){
		this.found = found;
	}

	public long getStart(){
		return start;
	}

	public void setStart(long start){
		this.start = start;
	}

	public int getRows(){
		return rows;
	}

	public void setRows(int rows){
		this.rows = rows;
	}

	public int getQtime(){
		return qtime;
	}

	public void setQtime(int qtime){
		this.qtime = qtime;
	}
}

