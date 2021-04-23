package com.banque.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration sous la forme d'une classe Java. <br/>
 */
@Configuration
@PropertySource("classpath:spring/database.properties")
@ComponentScan({ "com.banque.dao", "com.banque.service" })
@EnableTransactionManagement
public class SpringConfigurationData {

	@Bean(destroyMethod = "close")
	public DataSource dataSource(@Value("${db.driver}") String driver, @Value("${db.url}") String url,
			@Value("${db.login}") String login, @Value("${db.password}") String pwd) {
		BasicDataSource resu = new org.apache.commons.dbcp2.BasicDataSource();
		resu.setDriverClassName(driver);
		resu.setUrl(url);
		resu.setUsername(login);
		resu.setPassword(pwd);
		return resu;
	}

	@Bean
	public org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory(DataSource dataSource,
			@Value("${hibernate.dialect}") String dialect, @Value("${hibernate.connection.pool_size}") Integer pSize,
			@Value("${hibernate.cache.use_second_level_cache}") Boolean useCache) {
		org.springframework.orm.hibernate5.LocalSessionFactoryBean resu = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
		resu.setDataSource(dataSource);
		resu.setMappingResources("hibernate/compte.hbm.xml", "hibernate/operation.hbm.xml",
				"hibernate/utilisateur.hbm.xml");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.connection.pool_size", pSize);
		hibernateProperties.put("hibernate.cache.use_second_level_cache", useCache);
		resu.setHibernateProperties(hibernateProperties);
		return resu;
	}

	@Bean
	public org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {
		return new org.springframework.orm.hibernate5.HibernateTransactionManager(sessionFactory);
	}

}
