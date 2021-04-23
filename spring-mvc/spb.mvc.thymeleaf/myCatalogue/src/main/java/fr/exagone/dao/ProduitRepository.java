package fr.exagone.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.exagone.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	/**
	 * recherche par designation avec pagination.
	 * @param mc
	 * @param pageable
	 * @return
	 */
	public Page<Produit> findByDesignationContains(String mc, Pageable pageable);
	
	/**
	 * recherche par designation et prix minimum
	 * 
	 * @param mc
	 * @param prix
	 * @param pageable
	 * @return
	 */
	@Query("select p from Produit p where p.designation like :x and p.prix>:y")
	public Page<Produit> chercherParDesignationEtPrixMinimum(
			@Param("x")String mc, @Param("y") double prix, Pageable pageable);
	
}
