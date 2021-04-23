package com.banque.web.controller.rest.ex;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * Represente une exception dont l'une des methodes donnera un flux Json
 */
public class JSONException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	public JSONException() {
		super();
	}

	/**
	 * Constructeur.
	 *
	 * @param message
	 *            un message
	 * @param cause
	 *            une cause
	 */
	public JSONException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructeur.
	 *
	 * @param message
	 *            un message
	 */
	public JSONException(String message) {
		super(message);
	}

	/**
	 * Constructeur.
	 *
	 * @param cause
	 *            une cause
	 */
	public JSONException(Throwable cause) {
		super(cause);
	}

	/**
	 * Donnera l'exception en format String JSON.
	 *
	 * @return l'exception en format String JSON.
	 */
	public String toJsonString() {
		return this.toJson().toString();
	}

	/**
	 * Donnera l'exception sous forme d'un objet JSON.
	 *
	 * @return l'exception sous la forme d'un objet JSON.
	 */
	public JSONObject toJson() {
		JSONObject expObj = new JSONObject();
		if (this.getCause() != null) {
			try (PrintWriter sw = new PrintWriter(new StringWriter());) {
				this.getCause().printStackTrace(sw);
				expObj.put("errorCause", sw.toString());
			} catch (Exception e) {
				JSONException.LOG.error("Erreur lors de la recuperation de la cause", e);
			}
		}
		if ((this.getMessage() != null) && !this.getMessage().trim().isEmpty()) {
			expObj.put("errorMessage", this.getMessage());
		}
		return expObj;
	}

	/**
	 * @deprecated use toJsonString() instead
	 */
	@Deprecated
	@Override
	public String toString() {
		return this.toJsonString();
	}
}
