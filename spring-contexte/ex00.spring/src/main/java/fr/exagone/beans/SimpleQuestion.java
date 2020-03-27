package fr.exagone.beans;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import fr.exagone.beans.providers.ApplicationContextProvider;

public class SimpleQuestion implements DisposableBean, InitializingBean {

	private static final Logger LOG = LogManager.getLogger();
	
	private Integer id;
	
	private String label;
	
	private Map<String, String> answers;

	private ApplicationContextProvider appContext = new ApplicationContextProvider();
	
	
	public SimpleQuestion() {
	}
	
	public SimpleQuestion(Integer id, String label, Map<String, String> answers) {
		super();
		this.id = id;
		this.label = label;
		this.answers = answers;
	}
	
	public void initialiser() {
		TestBean tb = appContext.getApplicactionContext().getBean("testBean", TestBean.class);
		SimpleQuestion.LOG.info("[SimpleQuestion]: in Init method : TestBean = " + tb);
	}
	
	public void detruire() {
		SimpleQuestion.LOG.info("[SimpleQuestion]: in Detruire method");
	}
	
	public void displayInfo(){  
	    System.out.println("question id:" + id);  
	    System.out.println("question name:" + this.getLabel());  
	    System.out.println("Answers....");  
	    Set<Entry<String, String>> set=answers.entrySet();  
	    Iterator<Entry<String, String>> itr=set.iterator();  
	    while(itr.hasNext()){  
	        Entry<String,String> entry=itr.next();  
	        System.out.println("Answer:"+entry.getKey()+" Posted By:"+entry.getValue());  
	    }  
	} 
	
	@Override
	public void destroy() throws Exception {
		LOG.info("[SimpleQuestion] : disposableBean - in destroy method");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("[SimpleQuestion] : initialiseBean - afterPropertiesSet");
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{question: id = ").append(this.getId()).append(",");
		builder.append("label = ").append(this.getLabel());
		builder.append("}");
		LOG.info(builder.toString());
		return builder.toString();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}



	
	
}
