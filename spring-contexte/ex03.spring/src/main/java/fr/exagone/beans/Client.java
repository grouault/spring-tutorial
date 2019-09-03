package fr.exagone.beans;

import java.util.List;

public class Client {

	private String nom;
	
	private String prenom;
	
	private String age;
	
	private List<Adresse> adresses;

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
