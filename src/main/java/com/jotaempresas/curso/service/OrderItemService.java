package com.jotaempresas.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotaempresas.curso.entity.Category;
import com.jotaempresas.curso.entity.OrderItem;
import com.jotaempresas.curso.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	// TODOS USUARIOS
	public List<OrderItem> findAll(){		
		return orderItemRepository.findAll();
	}
	
	//USUARIO POR AI
	public OrderItem findById(Long id) {
		 return orderItemRepository.findById(id).orElse(null);
	}
	
	
	// DELETAR USUARIO AI
	public void DeleteId(Long id) {
		orderItemRepository.deleteById(id);
	}

}
