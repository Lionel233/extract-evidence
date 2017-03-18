package model;

/**
 * 证据记录
 *
 */
public class EvRecord {

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

	public EvRecord() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCommiter() {
		return commiter;
	}

	public void setCommiter(String commiter) {
		this.commiter = commiter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
