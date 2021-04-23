package com.banque.web.controller.rest.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Classe qui nous permet d'expliquer a Jasckson comment serialiser nos
 * exceptions en format Json.
 */
public class JSONExceptionSerializer extends StdSerializer<JSONException> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public JSONExceptionSerializer() {
		super(JSONException.class);
	}

	@Override
	public void serialize(JSONException pValue, JsonGenerator pGen, SerializerProvider pProvider) throws IOException {
		pGen.writeStartObject();
		if (pValue.getMessage() != null && !pValue.getMessage().trim().isEmpty()) {
			pGen.writeStringField("message", pValue.getMessage());
		} else {
			JSONExceptionSerializer.LOG.warn("Utilisation du message par defaut");
			pGen.writeStringField("message", "Une erreur est survenue.");
		}
		if (pValue.getCause() != null) {
			try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);) {
				pValue.getCause().printStackTrace(pw);
				pGen.writeStringField("cause", sw.toString());
				pGen.writeStringField("classe", pValue.getCause().getClass().getName());
			} catch (Exception e) {
				JSONExceptionSerializer.LOG.error("Erreur lors de la recuperation de la cause", e);
			}
		} else {
			pGen.writeStringField("classe", pValue.getClass().getName());
			pGen.writeNullField("cause");
		}
		pGen.writeEndObject();
	}

}
