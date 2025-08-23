package com.jotaempresas.curso.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jotaempresas.curso.entity.User;
import com.jotaempresas.curso.service.UserService;

// @RestController  = anotações que informam que essa classe e um recurso web controlado por um controlador rest
// @RequestMapping(value= "/User") =  nome do caminho

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	// GET /users
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// GET /users/{id}
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = userService.findById(id);

		if (obj == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id);
		}

		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/email/{email}") // Evitar conflito com GET /users/{id}
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
	    Optional<User> obj = userService.findByEmail(email);

	    if (obj.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com Email: " + email);
	    }

	    return ResponseEntity.ok(obj.get());
	}
	
	// DELETE /users/deletar/{id}
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		if (userService.findById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão. ID: " + id);

		}

		userService.DeleteId(id); // nome do método no service em camelCase
		return ResponseEntity.ok().build();
	}

	// INSERIR USUARIO
	@PostMapping("/insert")
	public ResponseEntity<User> insert(@RequestBody User user) {
		
	    // 1) Verificação de dados inválidos
	    if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome, email e senha são obrigatórios.");
	    }

	    // 2) Verificação de conflito (email já existente)
	    Optional<User> existingUser = userService.findByEmail(user.getEmail());
	    if (existingUser.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário com este email: " + user.getEmail());
	    }

	    try {
	        // 3) Se tudo ok, salvar usuário
	        User savedUser = userService.salverUser(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	    } catch (Exception e) {
	        // 4) Caso aconteça erro inesperado no servidor
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado ao salvar usuário", e);
	    }
	}

	
}
