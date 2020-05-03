package fr.exagone.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import fr.exagone.entity.IFooEntity;
import fr.exagone.service.IFooService;
import fr.exagone.service.ex.FonctionnelleException;

@Component
public class FooServiceImpl implements IFooService {

	/** Main log. */
	private static final Logger LOG = LogManager.getLogger();
	
	@Override
	public IFooEntity getFoo(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IFooEntity getFoo(String name) throws FonctionnelleException {
		LOG.info("insert foo service");
		throw new FonctionnelleException("get foo erreur");
	}

	@Override
	public void insertFoo(IFooEntity foo) {
		LOG.info("insert foo service");
		try {

			throw new FonctionnelleException("test erreur insertFoo");
		
		} catch (FonctionnelleException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}

	@Override
	public void updateFoo(IFooEntity foo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteFoo(IFooEntity foo) {
		throw new UnsupportedOperationException();
	}

}
