package com.sip.ams.entities;

public class Session {

	private String titre;
	private String logo;
	private String description;

	public Session(String titre, String logo, String description) {
		super();
		this.titre = titre;
		this.logo = logo;
		this.description = description;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Session [titre=" + titre + ", logo=" + logo + ", description=" + description + "]";
	}

	public Session() {
		super();
	}
	

}
