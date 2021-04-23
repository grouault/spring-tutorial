package com.banque.web.controller.rest.ex;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Represente une exception dont l'une des methodes donnera un flux Json
 */
@JsonSerialize(using = JSONExceptionSerializer.class)
public class JSONException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public JSONException() {
		super();
	}

	/**
	 * Constructeur.
	 *
	 * @param pException
	 *            l'exception
	 */
	public JSONException(Throwable pException) {
		super(pException.getMessage(), pException);
	}

}
