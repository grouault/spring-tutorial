package fr.exagone.beans;

public class Voiture {

	private String marque;
	
	private String modele;

	public Voiture() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}
	
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("marque:").append(this.getMarque())
			.append(", ")
			.append("modele:").append(this.getModele());
		return toReturn.toString();
	}
	
}
