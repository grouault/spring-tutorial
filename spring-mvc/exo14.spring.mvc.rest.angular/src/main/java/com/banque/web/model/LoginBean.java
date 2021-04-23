package com.banque.web.model;

import java.io.Serializable;

/**
 * Login Bean. <br/>
 */
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	/**
	 * Constructeur de l'objet.
	 */
	public LoginBean() {
		super();
	}

	/**
	 * Recupere la valeur du login.
	 *
	 * @return la valeur du login.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Recupere la valeur du password.
	 *
	 * @return la valeur du password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Modifie la valeur du login.
	 *
	 * @param aValue
	 *            la valeur du login.
	 */
	public void setLogin(String aValue) {
		if (aValue == null || aValue.trim().length() == 0) {
			this.login = null;
		} else {
			this.login = aValue;
		}
	}

	/**
	 * Modifie la valeur du password.
	 *
	 * @param aValue
	 *            la valeur du password.
	 */
	public void setPassword(String aValue) {
		if (aValue == null || aValue.trim().length() == 0) {
			this.password = null;
		} else {
			this.password = aValue;
		}
	}

	@SuppressWarnings("squid:S2068")
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [login=");
		builder.append(this.login);
		builder.append(", password=Xxxx]");
		return builder.toString();
	}

}
