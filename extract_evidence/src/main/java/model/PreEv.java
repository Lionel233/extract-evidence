package model;

import java.util.ArrayList;

/**
 * 全文Model
 * 
 * 包含证据记录以及提取到的原文 content为提取到的原文 source为原文位置(ZJD,QSZJD,BSZJD,XS,BSSLD)
 *
 */
public class PreEv {

	private ArrayList<EvPara> evParaList = new ArrayList<EvPara>();
	private String path; // 文件路径

	private ArrayList<Litigant> litigantList = new ArrayList<Litigant>();

	public PreEv() {
		super();
	}

	public ArrayList<EvPara> getEvParaList() {
		return evParaList;
	}

	public void setEvParaList(ArrayList<EvPara> evParaList) {
		this.evParaList = evParaList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<Litigant> getLitigantList() {
		return litigantList;
	}

	public void setLitigantList(ArrayList<Litigant> litigantList) {
		this.litigantList = litigantList;
	}

}
