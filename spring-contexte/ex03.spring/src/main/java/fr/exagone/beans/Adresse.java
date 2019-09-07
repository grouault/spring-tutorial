package fr.exagone.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Adresse {

	private String rue;
	private String ville;
	private String codePostal;
	private String pays;
	
	private static Logger LOG = LogManager.getLogger(Adresse.class);

	@PostConstruct
	public void initialize() {
		Adresse.LOG.info("init dans Adresse");
	}
	
	@PreDestroy
	public void destroy() {
		Adresse.LOG.info("destroy dans Adresse");
	}
	
	public Adresse() {
	}
	
	public Adresse(String rue, String ville, String codePostal, String pays) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("[rue:").append(this.getRue() )
			.append(", ")
			.append("ville:").append(this.getVille())
			.append(", ")
			.append("codePostal:").append(this.getCodePostal())
			.append(", ")
			.append("pays:").append(this.getPays())
			.append("]");
		return toReturn.toString();
	}
	
	
	
}
