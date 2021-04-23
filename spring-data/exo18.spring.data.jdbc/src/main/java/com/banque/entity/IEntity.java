package com.banque.entity;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

/**
 * Interface qui va representer une entite.
 */
public interface IEntity extends Serializable, Persistable<Integer> {

	/**
	 * Recupere l'id du compte.
	 *
	 * @return l'id du compte.
	 */
	@Override
	public abstract Integer getId();

	/**
	 * Fixe l'id du compte.
	 *
	 * @param unId
	 *            l'id du compte.
	 */
	public abstract void setId(Integer unId);

}