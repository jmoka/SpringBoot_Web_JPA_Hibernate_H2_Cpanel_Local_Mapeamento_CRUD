package com.jotaempresas.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotaempresas.curso.entity.Product;
import com.jotaempresas.curso.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	// TODOS USUARIOS
	public List<Product> findAll(){		
		return productRepository.findAll();
	}
	
	//USUARIO POR AI
	public Product findById(Long id) {
		 return productRepository.findById(id).orElse(null);
	}
	
	
	// DELETAR USUARIO AI
	public void DeleteId(Long id) {
		productRepository.deleteById(id);
	}

}
