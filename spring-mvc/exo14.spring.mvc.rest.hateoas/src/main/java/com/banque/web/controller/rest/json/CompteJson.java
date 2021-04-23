package com.banque.web.controller.rest.json;

import java.math.BigDecimal;

import com.banque.entity.ICompteEntity;

/**
 * Le bean qui represente un Compte en format Json. <br>
 *
 * Rappel : les id des entites ne sont pas visible dans les flux Json en
 * HATEOAS.
 */
public class CompteJson extends AbstractJson<ICompteEntity> {

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pEntity
	 *            entite associee
	 */
	public CompteJson(ICompteEntity pEntity) {
		super(pEntity);
	}

	public BigDecimal getTaux() {
		return this.getEntity().getTaux();
	}

	public BigDecimal getDecouvert() {
		return this.getEntity().getDecouvert();
	}

	public String getLibelle() {
		return this.getEntity().getLibelle();
	}

	public BigDecimal getSolde() {
		return this.getEntity().getSolde();
	}

	public Integer getUtilisateurId() {
		return this.getEntity().getUtilisateurId();
	}
}