package com.banque.web.controller.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.banque.web.controller.rest.ex.JSONException;

/**
 * Gestion globale des erreurs.
 */
@ControllerAdvice(basePackages = { "com.banque.web.controller.rest" })
public class ExceptionControllerAdvice {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Declaration de notre gestionnaire d'erreur pour tous les controleurs dans le
	 * packahe "com.banque.web.controller.rest".
	 *
	 * @param ex
	 *            une exception
	 * @return l'exception sous un format JSON et un http status
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONException> exceptionHandler(Exception ex) {
		JSONException error = new JSONException(ex);
		ResponseEntity<JSONException> resu = new ResponseEntity<JSONException>(error, HttpStatus.BAD_REQUEST);
		ExceptionControllerAdvice.LOG.error("Erreur ratrapee type={}", ex.getClass().getName(), ex);
		return resu;
	}
}
