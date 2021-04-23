package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IOperationEntity;

/**
 * Gestion des operations.
 */
@Repository
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super();
	}

	@Override
	protected Class<IOperationEntity> getEntityClass() {
		return IOperationEntity.class;
	}

	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit)
			throws ExceptionDao {

		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug("selectCriteria sur {} avec unCompteId={} et unDebut={} et uneFin={} et pCreditDebit={}",
				this.getClass().getName(), String.valueOf(unCompteId), unDebut, uneFin, pCreditDebit);
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			StringBuilder request = new StringBuilder();
			request.append("select entity from ").append(this.getEntityClassName());
			request.append(" as entity where entity.compteId=:compteId");
			Map<String, Object> gaps = new HashMap<String, Object>();
			gaps.put("compteId", Integer.valueOf(unCompteId));
			if (unDebut != null && uneFin == null) {
				request.append(" and entity.date >= :dateDebut");
				gaps.put("dateDebut", unDebut);
			}

			if (uneFin != null && unDebut == null) {
				request.append(" and entity.date <= :dateFin");
				gaps.put("dateFin", uneFin);
			}

			if (uneFin != null && unDebut != null) {
				request.append(" and entity.date between :dateDebut and :dateFin");
				gaps.put("dateDebut", unDebut);
				gaps.put("dateFin", uneFin);
			}

			if (pCreditDebit != null) {
				if (pCreditDebit.booleanValue()) {
					request.append(" and entity.montant >= 0");
				} else {
					request.append(" and entity.montant <= 0");
				}
			}

			request.append(" order by entity.date DESC");

			OperationDAO.LOG.debug("Requete OQL: {}", request.toString());

			Query<IOperationEntity> queryObject = pSession.createQuery(request.toString(), this.getEntityClass());
			for (Map.Entry<String, Object> unGap : gaps.entrySet()) {
				queryObject.setParameter(unGap.getKey(), unGap.getValue());
			}

			result = queryObject.getResultList();
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}
}