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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotaempresas.curso.entity.User;
import com.jotaempresas.curso.entity.execption.ResourceBadRequestException;
import com.jotaempresas.curso.entity.execption.ResourceConflictException;
import com.jotaempresas.curso.entity.execption.ResourceNotFoundException;
import com.jotaempresas.curso.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    // GET /users
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = userService.findById(id);
        String msg = "Usuário com ID não encontrado";
        if (obj == null) {
            throw new ResourceNotFoundException(msg, id);
        }
        return ResponseEntity.ok(obj);
    }

    // GET /users/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> obj = userService.findByEmail(email);
        if (obj.isEmpty()) {
        	String msg = "Usuário com Email não encontrado";
            throw new ResourceNotFoundException(msg, obj);
        }
        return ResponseEntity.ok(obj.get());
    }

    // DELETE /users/deletar/{id}
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	
        User obj = userService.findById(id); // busca o user por ai
        
        if (obj == null) {
        	// chama a excessão personalizada passando o text e o id
        	String msg = "Usuário não encontrado com Id: ";
            throw new ResourceNotFoundException(msg , id); // class criada para retornar codigo 404
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // POST /users/insert
    @PostMapping("/insert")
    public ResponseEntity<User> insert(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
        	String msg = "Obrigatório Nome, Email e Senha";
            throw new ResourceBadRequestException(msg);
        }

        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
        	String msg = "Já existe usuário com este email: ";
            throw new ResourceConflictException(msg + user.getEmail());
        }

        User savedUser = userService.salverUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // PUT /users/updateUser/{id}
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateDataUser(id, user);
        if (updatedUser == null) {
        	String msg = "Usuário não encontrado com ID : ";
            throw new ResourceNotFoundException(msg, id);
        }
        return ResponseEntity.ok(updatedUser);
    }
}
