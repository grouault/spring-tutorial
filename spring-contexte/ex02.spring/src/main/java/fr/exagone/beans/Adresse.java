package fr.exagone.beans;

public class Adresse {

	private String rue;
	private String ville;
	private String codePostal;
	private String pays;
	
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
