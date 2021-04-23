package fr.exagone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.exagone.dao.ProduitRepository;
import fr.exagone.entities.Produit;

@SpringBootApplication
public class MyCatalogueApplication implements CommandLineRunner {

	@Autowired
	private ProduitRepository produitRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		produitRepository.save(new Produit(null, "Ordi HP 5643", 1200, 10));
		produitRepository.save(new Produit(null, "Imprimante HP 1122", 345, 11));
		produitRepository.save(new Produit(null, "Smart phone 5643", 500, 15));
		
		// recuperation de tous Produits pagination
		Page<Produit> produits = produitRepository.findByDesignationContains("Imprimante", PageRequest.of(0, 2));
		produits.getContent().forEach(p -> {
			System.out.println(p.toString());
		});
		
		// recuperation des produits superieur à un prix minimum
		Page<Produit> produitsPrixMin = produitRepository.chercherParDesignationEtPrixMinimum("%H%", 600, PageRequest.of(0, 3));
		produitsPrixMin.getContent().forEach((p) -> {
			System.out.println(p.toString());
		});
		
		
		
	}

}
