package com.jotaempresas.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotaempresas.curso.entity.User;


// @RestController  = anotações que informam que essa classe e um recurso web controlado por um controlador rest
// @RequestMapping(value= "/User") =  nome do caminho
@RestController
@RequestMapping(value= "/users")
public class UserResource {
	
	// Crias os EndPoints
	
	
	// buscar todos os Usuarios
	// colocar para informar ao http que esse método é u GET
	@GetMapping
	public ResponseEntity<User> findAll(){
		
		// Teste de Criar um usuario
		User u = new User(1L, "Maria", "maria@gmail.com", "999999", "123456");
		
		// retorna um responseEntity confirmando com ok trazendo o corpo do body de usuario
		return ResponseEntity.ok().body(u);
	}
	

}
