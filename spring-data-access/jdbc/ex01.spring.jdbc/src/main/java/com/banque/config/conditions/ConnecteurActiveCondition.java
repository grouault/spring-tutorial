package com.banque.config.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConnecteurActiveCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String isConnecteurActive = context.getEnvironment().getProperty("app.isConnecteurActive");
		return Boolean.valueOf(isConnecteurActive);
	}

}
