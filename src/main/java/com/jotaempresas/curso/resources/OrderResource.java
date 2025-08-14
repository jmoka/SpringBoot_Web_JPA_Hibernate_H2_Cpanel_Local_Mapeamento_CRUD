package com.jotaempresas.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jotaempresas.curso.entity.Order;
import com.jotaempresas.curso.service.OrderService;


// @RestController  = anotações que informam que essa classe e um recurso web controlado por um controlador rest
// @RequestMapping(value= "/Order") =  nome do caminho

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	// GET /users
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// GET /users/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = orderService.findById(id);

		if (obj == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order não encontrada com ID: " + id);
		}

		return ResponseEntity.ok(obj);
	}

	// DELETE /users/deletar/{id}
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		if (orderService.findById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão. ID: " + id);

		}

		orderService.DeleteId(id); // nome do método no service em camelCase
		return ResponseEntity.ok().build();
	}
}
