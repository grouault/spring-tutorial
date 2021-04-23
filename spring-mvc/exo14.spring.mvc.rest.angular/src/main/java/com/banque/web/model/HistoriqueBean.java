package com.banque.web.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Form pour l'historique. <br/>
 */
public class HistoriqueBean implements Serializable {
	private static final Logger LOG = LogManager.getLogger();
	// Angular nous donnes la date sous le format suivant :
	// "2016-08-31T22:00:00.000Z"
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.mmm'Z'";
	private static final long serialVersionUID = 1L;

	private String cptId;

	private String dateDebut;
	private String dateFin;
	private String credit;
	private String debit;

	/**
	 * Constructeur de l'objet.
	 */
	public HistoriqueBean() {
		super();
	}

	/**
	 * Recupere la propriete <i>cptId</i>.
	 *
	 * @return the cptId la valeur de la propriete.
	 */
	public String getCptId() {
		return this.cptId;
	}

	/**
	 * Recupere la propriete <i>cptId</i>.
	 *
	 * @return the cptId la valeur de la propriete.
	 */
	public Integer getCptIdAsInteger() {
		try {
			return Integer.valueOf(this.cptId);
		} catch (Exception e) {
			HistoriqueBean.LOG.trace("Erreur sur l'id", e);
		}
		return null;
	}

	/**
	 * Fixe la propriete <i>cptId</i>.
	 *
	 * @param pCptId
	 *            la nouvelle valeur pour la propriete cptId.
	 */
	public void setCptId(String pCptId) {
		this.cptId = pCptId;
	}

	/**
	 * Recupere la propriete <i>dateDebut</i>.
	 *
	 * @return the dateDebut la valeur de la propriete.
	 */
	public String getDateDebut() {
		return this.dateDebut;
	}

	/**
	 * Recupere la propriete <i>dateDebut</i>.
	 *
	 * @return the dateDebut la valeur de la propriete.
	 */
	public java.util.Date getDateDebutAsDate() {
		if (this.getDateDebut() == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(HistoriqueBean.PATTERN);
		try {
			return sdf.parse(this.getDateDebut());
		} catch (Exception e) {
			HistoriqueBean.LOG.trace("Erreur sur la date", e);
		}
		return null;
	}

	/**
	 * Fixe la propriete <i>dateDebut</i>.
	 *
	 * @param pDateDebut
	 *            la nouvelle valeur pour la propriete dateDebut.
	 */
	public void setDateDebut(String pDateDebut) {
		if (pDateDebut == null || pDateDebut.trim().length() == 0) {
			this.dateDebut = null;
		} else {
			this.dateDebut = pDateDebut;
		}
	}

	/**
	 * Recupere la propriete <i>dateFin</i>.
	 *
	 * @return the dateFin la valeur de la propriete.
	 */
	public String getDateFin() {
		return this.dateFin;
	}

	/**
	 * Recupere la propriete <i>dateFin</i>.
	 *
	 * @return the dateFin la valeur de la propriete.
	 */
	public java.util.Date getDateFinAsDate() {
		if (this.getDateFin() == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(HistoriqueBean.PATTERN);
		try {
			return sdf.parse(this.getDateFin());
		} catch (Exception e) {
			HistoriqueBean.LOG.trace("Erreur sur la date", e);
		}
		return null;
	}

	/**
	 * Fixe la propriete <i>dateFin</i>.
	 *
	 * @param pDateFin
	 *            la nouvelle valeur pour la propriete dateFin.
	 */
	public void setDateFin(String pDateFin) {
		if (pDateFin == null || pDateFin.trim().length() == 0) {
			this.dateFin = null;
		} else {
			this.dateFin = pDateFin;
		}
	}

	/**
	 * Recupere la propriete <i>credit</i>.
	 *
	 * @return the credit la valeur de la propriete.
	 */
	public String getCredit() {
		return this.credit;
	}

	/**
	 * Recupere la propriete <i>credit</i>.
	 *
	 * @return the credit la valeur de la propriete.
	 */
	public Boolean getCreditAsBoolean() {
		return "true".equals(this.getCredit()) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Fixe la propriete <i>credit</i>.
	 *
	 * @param pCredit
	 *            la nouvelle valeur pour la propriete credit.
	 */
	public void setCredit(String pCredit) {
		this.credit = pCredit;
	}

	/**
	 * Recupere la propriete <i>debit</i>.
	 *
	 * @return the debit la valeur de la propriete.
	 */
	public String getDebit() {
		return this.debit;
	}

	/**
	 * Recupere la propriete <i>debit</i>.
	 *
	 * @return the debit la valeur de la propriete.
	 */
	public Boolean getDebitAsBoolean() {
		return "true".equals(this.getDebit()) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Fixe la propriete <i>debit</i>.
	 *
	 * @param pDebit
	 *            la nouvelle valeur pour la propriete debit.
	 */
	public void setDebit(String pDebit) {
		this.debit = pDebit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [cptId=");
		builder.append(this.cptId);
		builder.append(", dateDebut=");
		builder.append(this.dateDebut);
		builder.append(", dateFin=");
		builder.append(this.dateFin);
		builder.append(", credit=");
		builder.append(this.credit);
		builder.append(", debit=");
		builder.append(this.debit);
		builder.append("]");
		return builder.toString();
	}

}
