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

import com.jotaempresas.curso.entity.OrderItem;
import com.jotaempresas.curso.service.OrderItemService;


// @RestController  = anotações que informam que essa classe e um recurso web controlado por um controlador rest
// @RequestMapping(value= "/Order") =  nome do caminho

@RestController
@RequestMapping(value = "/orderItem")
public class OerderItemResource {

	@Autowired
	private OrderItemService orderItemService;

	// GET /orderItem
	@GetMapping
	public ResponseEntity<List<OrderItem>> findAll() {
		List<OrderItem> list = orderItemService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// GET /category/{id}
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> findById(@PathVariable Long id) {
		OrderItem obj = orderItemService.findById(id);

		if (obj == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order não encontrada com ID: " + id);
		}

		return ResponseEntity.ok(obj);
	}

	// DELETE /orderItem/deletar/{id}
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		if (orderItemService.findById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado para exclusão. ID: " + id);

		}

		orderItemService.DeleteId(id); // nome do método no service em camelCase
		return ResponseEntity.ok().build();
	}
}
