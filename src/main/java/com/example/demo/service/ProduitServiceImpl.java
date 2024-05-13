package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.produits.dto.ProduitDTO;
import com.example.demo.repos.ProduitRepository;
import com.example.demo.service.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService {
	
	@Autowired
	ProduitRepository produitRepository;

	@Override
	public ProduitDTO saveProduit(Produit p) {
		return convertEntityToDto( produitRepository.save(p));
	}
	
	@Override
	public void deleteProduitById(Long id) {
		  produitRepository.deleteById(id);
	}
	@Override
	public ProduitDTO getProduit(Long id) {
		return convertEntityToDto( produitRepository.findById(id).get());
	}
	@Override
	public List<ProduitDTO> getAllProduits() {

	//Page 3 Spring boot/Angular 2023

		return produitRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

	//OU BIEN  
	/*List<Produit> prods = produitRepository.findAll();
	List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
	for (Produit p : prods)
	listprodDto.add(convertEntityToDto(p));
	return listprodDto;*/
	}
	@Autowired
		ModelMapper modelMapper ; 
	@Override
	public ProduitDTO convertEntityToDto(Produit produit) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
		
		return produitDTO ;
	/*return ProduitDTO.builder()
			.idProduit(produit.getIdProduit())
			.nomProduit(produit.getNomProduit())
			.prixProduit(produit.getPrixProduit())
			.dateCreation(p.getDateCreation())
			.categorie(produit.getCategorie())
			.build();*/

	}
	
	@Override
	public Produit convertDtoToEntity(ProduitDTO produitDto) {

		Produit produit = new Produit();
		produit = modelMapper.map(produitDto, Produit.class);
		return produit ;
	}
	@Override
	public ProduitDTO saveProduit(ProduitDTO p) {
		return convertEntityToDto( produitRepository.save(convertDtoToEntity(p)));
	}
	@Override
	public ProduitDTO updateProduit(ProduitDTO p) {
		return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
	}
	@Override
	public void deleteProduit(ProduitDTO p) {
		 produitRepository.delete(convertDtoToEntity(p) );
	}
}