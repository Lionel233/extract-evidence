package model;

/**
 * 证据记录
 *
 */
public class EvidenceRecord {

	/**
	 * 证据名称
	 */
	private String name;
	/**
	 * 证据种类
	 */
	private String type;
	
	/**
	 * 证据数量
	 * 一份/13张...
	 */
	private String number;
	/**
	 * 提交人
	 */
	private String commiter;
	/**
	 * 证据记录的value
	 */
	private String content;

	public EvidenceRecord(String content) {
		super();
		this.content = content;
	}

}
