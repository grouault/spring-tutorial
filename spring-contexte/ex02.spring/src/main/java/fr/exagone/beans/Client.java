package fr.exagone.beans;

public class Client {

	private String nom;
	
	private String prenom;
	
	private String age;
	
	private Adresse adresse;

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
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("nom:").append(this.getNom())
			.append(", ")
			.append("prenom:").append(this.getPrenom())
			.append(", ")
			.append("adresse").append(this.getAdresse());
		return toReturn.toString();
	
	}
	
	
	
}
