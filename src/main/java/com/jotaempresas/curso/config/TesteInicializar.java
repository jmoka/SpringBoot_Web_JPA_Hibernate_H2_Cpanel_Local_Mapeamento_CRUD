package com.jotaempresas.curso.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jotaempresas.curso.entity.Order;
import com.jotaempresas.curso.entity.User;
import com.jotaempresas.curso.repositories.OrderRepository;
import com.jotaempresas.curso.repositories.UserRepository;

// Essa anotação @Component indica que o Spring deve gerenciar esta classe como um Bean (componente da aplicação)
// Assim, será possível fazer injeção de dependência desta classe em outros lugares.
@Component
public class TesteInicializar {

    // Injetamos o UserRepository para que possamos acessar os métodos de persistência no banco de dados (save, findAll, etc)
    @Autowired
    private UserRepository userRepository;
    
    @Autowired OrderRepository OrderRepository;
    
    // Criamos dois objetos User com dados fictícios para popular o banco de dados
    // O 'null' é usado no ID pois o banco de dados (através do JPA) irá gerar automaticamente o ID    
    User u1 = new User(null, "Maria Brown II", "maria@gmail.com", "988888888", "123457");
    User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

    // Método que será responsável por inserir usuários automaticamente no banco de dados ao ser chamado
    public void insertUserAuto() {
        // Salvamos os dois usuários de uma só vez utilizando saveAll()
        // O método saveAll() recebe uma lista de objetos e persiste todos eles de uma vez no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
        
        // Mensagem de log no console informando que a operação foi realizada
        System.out.println("Usuarios inseridos com sucesso!");
    }
    
    public void insertOrderAuto() {
    	Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
    	Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
    	Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
    	
    	OrderRepository.saveAll(Arrays.asList(o1,o2,o3));
    }
}
