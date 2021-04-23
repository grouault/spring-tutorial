package fr.exagone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Produit")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Produit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String designation;
	private double prix;
	private double quantite;
	
}
