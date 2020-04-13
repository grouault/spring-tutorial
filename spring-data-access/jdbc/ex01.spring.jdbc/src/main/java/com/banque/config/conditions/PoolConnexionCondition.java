package com.banque.config.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PoolConnexionCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String isConnexionPool = context.getEnvironment().getProperty("app.isConnexionPool");
		return Boolean.valueOf(isConnexionPool);
	}

}
