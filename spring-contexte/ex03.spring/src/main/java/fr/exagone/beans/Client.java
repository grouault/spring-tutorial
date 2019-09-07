package fr.exagone.beans;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Client {
	
	private final static Logger LOG = LogManager.getLogger(Client.class);

	private String nom;
	
	private String prenom;
	
	private String age;
	
	private List<Adresse> adresses;

	public void initialiser() {
		Client.LOG.info("Dans mon Init du client");
	}
	
	public void detruire() {
		Client.LOG.info("Dans mon Destroy du client");
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("nom:").append(this.getNom())
			.append(", ")
			.append("prenom:").append(this.getPrenom())
			.append(", ")
			.append("adresse").append(this.getAdresses());
		return toReturn.toString();
	
	}
	
	
	
}
