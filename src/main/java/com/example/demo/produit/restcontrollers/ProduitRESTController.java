package com.example.demo.produit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Produit;
import com.example.demo.produits.dto.ProduitDTO;
import com.example.demo.service.ProduitService;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin("http://localhost:4200") //04/03
public class ProduitRESTController {
	@Autowired
	ProduitService produitService;
	
	@GetMapping("/all")
	public List<ProduitDTO> getAllProduits() {
		return produitService.getAllProduits();
	}
	
	@GetMapping("/{id}")
	public ProduitDTO getProduitById(@PathVariable("id") Long id) {
		return produitService.getProduit(id);
	}
	
	@PostMapping
	public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.saveProduit(produitDTO);
	}
	
	@PutMapping
	public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.updateProduit(produitDTO);
	}
	@DeleteMapping("/{id}")
	public void deleteProduit(@PathVariable("id") Long id) {
		 produitService.deleteProduitById(id);
	}
}
