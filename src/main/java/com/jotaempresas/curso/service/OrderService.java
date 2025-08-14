package com.jotaempresas.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotaempresas.curso.entity.Order;
import com.jotaempresas.curso.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	// TODOS USUARIOS
	public List<Order> findAll(){		
		return orderRepository.findAll();
	}
	
	//USUARIO POR AI
	public Order findById(Long id) {
		 return orderRepository.findById(id).orElse(null);
	}
	
	
	// DELETAR USUARIO AI
	public void DeleteId(Long id) {
		orderRepository.deleteById(id);
	}

}
