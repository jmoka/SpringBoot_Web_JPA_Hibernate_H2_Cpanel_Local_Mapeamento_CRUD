package com.jotaempresas.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotaempresas.curso.entity.Category;
import com.jotaempresas.curso.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// TODOS USUARIOS
	public List<Category> findAll(){		
		return categoryRepository.findAll();
	}
	
	//USUARIO POR AI
	public Category findById(Long id) {
		 return categoryRepository.findById(id).orElse(null);
	}
	
	
	// DELETAR USUARIO AI
	public void DeleteId(Long id) {
		categoryRepository.deleteById(id);
	}

}
