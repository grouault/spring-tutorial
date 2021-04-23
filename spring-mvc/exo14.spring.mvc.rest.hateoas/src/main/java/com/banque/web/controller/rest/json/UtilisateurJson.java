package com.banque.web.controller.rest.json;

import java.sql.Date;
import java.sql.Timestamp;

import com.banque.entity.IUtilisateurEntity;

/**
 * Le bean qui represente un utilisateur. <br>
 */
public class UtilisateurJson extends AbstractJson<IUtilisateurEntity> {

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pEntity
	 *            entite associee
	 */
	public UtilisateurJson(IUtilisateurEntity pEntity) {
		super(pEntity);
	}

	public String getTelephone() {
		return this.getEntity().getTelephone();
	}

	public Date getDateDeNaissance() {
		return this.getEntity().getDateDeNaissance();
	}

	public String getAdresse() {
		return this.getEntity().getAdresse();
	}

	public Integer getCodePostal() {
		return this.getEntity().getCodePostal();
	}

	public Boolean getSex() {
		return this.getEntity().getSex();
	}

	public String getLogin() {
		return this.getEntity().getLogin();
	}

	public String getPassword() {
		return this.getEntity().getPassword();
	}

	public String getNom() {
		return this.getEntity().getNom();
	}

	public String getPrenom() {
		return this.getEntity().getPrenom();
	}

	public Timestamp getDerniereConnection() {
		return this.getEntity().getDerniereConnection();
	}
}