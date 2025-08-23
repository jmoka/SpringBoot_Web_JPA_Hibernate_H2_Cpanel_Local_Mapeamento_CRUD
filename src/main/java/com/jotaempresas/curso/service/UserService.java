package com.jotaempresas.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotaempresas.curso.entity.User;
import com.jotaempresas.curso.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// TODOS USUARIOS
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// USUARIO POR AI
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	// USUARIO POR Email
	public Optional<User> findByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	
	
	// DELETAR USUARIO AI
	public void DeleteId(Long id) {
		userRepository.deleteById(id);
	}

	// INSERIR USUARIO
	public User salverUser(User user) {
		return userRepository.save(user);
	}

}
