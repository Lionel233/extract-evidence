package model;

import java.util.ArrayList;

/**
 * 全文Model
 * 
 * 包含证据记录以及提取到的原文 content为提取到的原文 source为原文位置(ZJD,QSZJD,BSZJD,XS,BSSLD)
 *
 */
public class QwModel {


	private String content1;
	private String content2;
	private String source1;
	private String source2;
	private String path;	//文件路径
	private ArrayList<EvidenceRecord> recordList;
	public QwModel(String content1, String source1) {
		super();
		this.content1 = content1;
		this.source1 = source1;
	}

	public QwModel(String content1, String source1, String path) {
		super();
		this.content1 = content1;
		this.source1 = source1;
		this.path = path;
	}

	public QwModel() {
		super();
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getSource1() {
		return source1;
	}

	public void setSource1(String source1) {
		this.source1 = source1;
	}

	public String getSource2() {
		return source2;
	}

	public void setSource2(String source2) {
		this.source2 = source2;
	}

	public ArrayList<EvidenceRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(ArrayList<EvidenceRecord> recordList) {
		this.recordList = recordList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
