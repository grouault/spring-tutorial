package fr.exagone.service.impl;

import org.springframework.stereotype.Component;

import fr.exagone.entity.IFooEntity;
import fr.exagone.service.IFooService;

@Component
public class DefaultFooService implements IFooService {

	@Override
	public IFooEntity getFoo(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IFooEntity getFoo(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void insertFoo(IFooEntity foo) {
		throw new UnsupportedOperationException();
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
