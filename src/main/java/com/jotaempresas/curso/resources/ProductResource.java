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

import com.jotaempresas.curso.entity.Category;
import com.jotaempresas.curso.entity.Product;
import com.jotaempresas.curso.service.ProductService;


// @RestController  = anotações que informam que essa classe e um recurso web controlado por um controlador rest
// @RequestMapping(value= "/Order") =  nome do caminho

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;

	// GET /product
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = productService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// GET /product/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = productService.findById(id);

		if (obj == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product não encontrada com ID: " + id);
		}

		return ResponseEntity.ok(obj);
	}

	// DELETE /product/deletar/{id}
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		if (productService.findById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product não encontrado para exclusão. ID: " + id);

		}

		productService.DeleteId(id); // nome do método no service em camelCase
		return ResponseEntity.ok().build();
	}
}
