package model;

import java.util.ArrayList;

public class EvPara {

	private String content;
	private String keyContent;
	private String evContent;
	private String commiter;
	/**
	 * 标号类特有属性
	 */
	private ArrayList<String> evContentList;
	private String source;
	private ArrayList<EvRecord> recordList;
	/**
	 * match种类/解析方法
	 */
	private String resolveType;

	public EvPara() {
		super();
	}

	public EvPara(String content, String source) {
		super();
		this.content = content;
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyContent() {
		return keyContent;
	}

	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getResolveType() {
		return resolveType;
	}

	public void setResolveType(String resolveType) {
		this.resolveType = resolveType;
	}

	public ArrayList<EvRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(ArrayList<EvRecord> recordList) {
		this.recordList = recordList;
	}

	public String getEvContent() {
		return evContent;
	}

	public void setEvContent(String evContent) {
		this.evContent = evContent;
	}

	public ArrayList<String> getEvContentList() {
		return evContentList;
	}

	public void setEvContentList(ArrayList<String> evContentList) {
		this.evContentList = evContentList;
	}
	public String getCommiter() {
		return commiter;
	}

	public void setCommiter(String commiter) {
		this.commiter = commiter;
	}

}
