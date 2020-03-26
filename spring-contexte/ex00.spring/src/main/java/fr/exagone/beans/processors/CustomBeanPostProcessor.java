package fr.exagone.beans.processors;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean PostProcessor that handles destruction of prototype beans
 * @author grouault
 *
 */
public class CustomBeanPostProcessor implements BeanPostProcessor, DisposableBean, BeanFactoryAware {

	/*
	 * 1.BeanFactoryAware This interface provides a callback method which receives a
	 * Beanfactory object. This BeanFactory object is used in the post-processor
	 * class to identify all prototype beans via its BeanFactory.isPrototype(String
	 * beanName) method.
	 * 
	 * 2. DisposableBean This interface provides a Destroy() callback method invoked
	 * by the Spring container. We will call the Destroy() methods of all our
	 * prototype beans from within this method.
	 * 
	 * 3. BeanPostProcessor Implementing this interface provides access to
	 * post-process callbacks from within which, we prepare an internal List<> of
	 * all prototype objects instantiated by the Spring container. We will later
	 * loop through this List<> to destroy each of our prototype beans.
	 */
	
	private static final Logger LOG  = LogManager.getLogger();
	
	private BeanFactory beanFactory;
	
	private List<Object> prototypeBeans = new LinkedList<>();
	
	@PostConstruct
	private void init() {
		LOG.info("beanFactory : " + beanFactory);
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		LOG.info("before initialisation : " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		LOG.info("after initialisation : " + beanName);
		if (beanFactory.isPrototype(beanName)) {
			synchronized (prototypeBeans) {
				prototypeBeans.add(bean);
			}
		}
		return bean;
	}

	@Override
	public void destroy() throws Exception {
		LOG.info("destroy");
		synchronized (prototypeBeans) {
			for (Object bean : prototypeBeans) {
				if (bean instanceof DisposableBean) {
					LOG.info("destroy - bean : " + bean);
					DisposableBean disposable = (DisposableBean)bean;
					disposable.destroy();
				}
			}
			prototypeBeans.clear();
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
