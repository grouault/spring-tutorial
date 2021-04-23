package com.banque.web.controller.rest.json;

import java.sql.Timestamp;

import com.banque.entity.IOperationEntity;

/**
 * Le bean qui represente une operation. <br>
 */
public class OperationJson extends AbstractJson<IOperationEntity> {

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pEntity
	 *            entite associee
	 */
	public OperationJson(IOperationEntity pEntity) {
		super(pEntity);
	}

	public Timestamp getDate() {
		return this.getEntity().getDate();
	}

	public String getLibelle() {
		return this.getEntity().getLibelle();
	}

	public Double getMontant() {
		return this.getEntity().getMontant();
	}

	public Integer getCompteId() {
		return this.getEntity().getCompteId();
	}
}