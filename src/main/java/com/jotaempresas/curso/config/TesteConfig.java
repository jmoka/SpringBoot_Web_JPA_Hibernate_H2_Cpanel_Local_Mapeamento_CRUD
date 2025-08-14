package com.jotaempresas.curso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Esta anotação @Configuration informa ao Spring que esta classe contém configurações para o projeto
@Configuration

// @Profile("test") indica que essa configuração só será ativada quando o profile 'test' estiver ativo
// Dessa forma, esse código só será executado em ambiente de teste (ex: H2 Database em memória)
@Profile("test")
public class TesteConfig implements CommandLineRunner {

    // Injeção de dependência da classe TesteInicializar, que contém a lógica de inserção de dados
    @Autowired
    private TesteInicializar testeInicializar;

    // O método run() é herdado da interface CommandLineRunner e será executado automaticamente
    // logo após a aplicação Spring Boot ser iniciada.
    @Override
    public void run(String... args) throws Exception {
        // Aqui chamamos o método responsável por popular o banco de dados com usuários fictícios
        testeInicializar.insertUserAuto();
        testeInicializar.insertOrderAuto();
        testeInicializar.insertCategory();
        testeInicializar.insertProduct();
    }
}
