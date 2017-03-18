package model;

public enum LitigantType {
	DEFENDANT("被告"), ACCUSER("原告"), PROSECUTIONSERVICE("公诉机关"), agent("代理人"), thirdParty(
			"第三人"), PlaintiffInIncidentalCivilAction("附带民事诉讼原告人");

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private LitigantType(String type) {
		this.type = type;
	}

	public static LitigantType getLitigantType(String type) {
		for (LitigantType litigantType : LitigantType.values()) {
			if (litigantType.getType().equals(type)) {
				return litigantType;
			}
		}
		return null;
	}

}
