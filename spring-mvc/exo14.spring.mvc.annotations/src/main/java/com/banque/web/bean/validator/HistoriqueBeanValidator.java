package com.banque.web.bean.validator;

import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.banque.web.bean.HistoriqueBean;

/**
 * Validateur pour l'historique. <br/>
 */
public class HistoriqueBeanValidator implements Validator {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	public boolean supports(Class<?> pArg0) {
		return HistoriqueBean.class == pArg0;
	}

	@Override
	public void validate(Object aBean, Errors errors) {
		HistoriqueBean hb = (HistoriqueBean) aBean;
		if (hb.getCptId() == null) {
			HistoriqueBeanValidator.LOG.warn("Validation error {} pour cptId", this.getClass().getName());
			errors.rejectValue("cptId", "error.cpt.id.empty");
		}

		String dateFormat = "yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		java.util.Date lDateDebut = null;
		if (hb.getDateDebut() != null) {
			try {
				lDateDebut = sdf.parse(hb.getDateDebut());
			} catch (Exception e) {
				HistoriqueBeanValidator.LOG.warn("Validation error {} pour dateDebut", this.getClass().getName(), e);
				errors.rejectValue("dateDebut", "error.date.debut.invalid");
			}
		}
		java.util.Date lDateFin = null;
		if (hb.getDateFin() != null) {
			try {
				lDateFin = sdf.parse(hb.getDateFin());
			} catch (Exception e) {
				HistoriqueBeanValidator.LOG.warn("Validation error {} pour dateFin", this.getClass().getName(), e);
				errors.rejectValue("dateFin", "error.date.fin.invalid");
			}
		}
		if (lDateFin != null && lDateDebut != null && lDateDebut.after(lDateFin)) {
			HistoriqueBeanValidator.LOG.warn("Validation error {} pour dateFin", this.getClass().getName());
			errors.rejectValue("dateFin", "error.dates.invalid");
		}
	}

}
