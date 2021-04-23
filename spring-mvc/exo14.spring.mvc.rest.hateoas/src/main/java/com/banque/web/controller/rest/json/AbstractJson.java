package com.banque.web.controller.rest.json;

import org.springframework.hateoas.ResourceSupport;

import com.banque.entity.IEntity;

/**
 * Le bean qui represente une entite. <br>
 *
 * Rappel : les id des entites ne sont pas visible dans les flux Json en
 * HATEOAS.
 */
abstract class AbstractJson<T extends IEntity> extends ResourceSupport {

	private final T entity;

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pEntity
	 *            l'entite associee
	 */
	protected AbstractJson(T pEntity) {
		super();
		this.entity = pEntity;
	}

	/**
	 * Recupere l'entite associee.
	 *
	 * @return l'entite associee.
	 */
	protected final T getEntity() {
		return this.entity;
	}
}