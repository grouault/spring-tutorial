package com.banque.config;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;

import com.banque.beans.AppBean;
import com.banque.config.conditions.ConnecteurActiveCondition;
import com.banque.config.conditions.PoolConnexionCondition;
import com.banque.db.ConnecteurDb;
import com.banque.db.ConnexionParams;
import com.banque.db.PoolConnectionDb;
import com.banque.providers.ApplicationContextProvider;

@Configuration
@PropertySource(value= {
		"classpath:spring/database.properties",
		"classpath:spring/app.properties"
})
@ComponentScan("com.banque.providers")
public class DbConfig {

	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private ApplicationContextProvider contextProvider;
	
	@Bean
	public AppBean getApplicationConfig(
			@Value("${app.isConnexionPool}") Boolean isConnexionPool,
			@Value("${app.isConnecteurActive}") Boolean isConnecteurActive) {
		AppBean appConfig = new AppBean();
		appConfig.setIsPoolConnexion(isConnexionPool);
		appConfig.setIsConnecteurActive(isConnecteurActive);
		return appConfig;
	}
	
	@Bean
	public ConnexionParams getParamConnection( 
			@Value("${bdd.driver}") String driver, 
			@Value("${bdd.url}") String url, 
			@Value("${bdd.login}") String login, 
			@Value("${bdd.password}") String pwd
		) {
		
		ConnexionParams params = new ConnexionParams(driver, url, login, pwd);
		LOG.info("instanciation params = {}", params);
		return params;
	
	}
	
	@Bean
	@Scope("prototype")
	@Conditional(ConnecteurActiveCondition.class)
	public ConnecteurDb getConnecteurDb() throws SQLException {
		ConnexionParams params = this.contextProvider.getApplicactionContext().getBean(ConnexionParams.class);
		ConnecteurDb connecteurDb = new ConnecteurDb(params);
		return connecteurDb;
	}
	
	@Bean
	@Conditional(PoolConnexionCondition.class)
	public PoolConnectionDb getPoolConnectionDb() throws SQLException {
		// parametres de la connexion
		Integer nbConnexions = 10;
		ConnexionParams params = this.contextProvider.getApplicactionContext().getBean(ConnexionParams.class);
		// recuperation des paramètres
		PoolConnectionDb pool = new PoolConnectionDb(nbConnexions, params);
		LOG.info("instanciation pool = {}", pool);
		return pool;
	}

}
