package fr.exagone.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.exagone.dao.ProduitRepository;
import fr.exagone.entities.Produit;

@Controller
public class ProduitController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}

	@GetMapping(path="/products")
	public String products(Model model, 
			@RequestParam(name="page", defaultValue = "0") int noPage, 
			@RequestParam(name="size", defaultValue = "5") int size,
			@RequestParam(name="motCle", defaultValue = "") String motCle) {
		
		Page<Produit> pageProduits = produitRepository.findByDesignationContains(motCle, PageRequest.of(noPage, size));
		model.addAttribute("pageProduits", pageProduits);
		model.addAttribute("size", size);
		model.addAttribute("currentPage", noPage);
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
		model.addAttribute("motCle", motCle);
		
		return "products";
		
	}
	
	@GetMapping(path="/deleteProduit")
	public String delete(Long id,String page, String size, String motCle) {
		produitRepository.deleteById(id);
		// redirection
		return "redirect:/products?page="+page+"&motCle="+motCle+"&size="+size;
	}
	
	
}
