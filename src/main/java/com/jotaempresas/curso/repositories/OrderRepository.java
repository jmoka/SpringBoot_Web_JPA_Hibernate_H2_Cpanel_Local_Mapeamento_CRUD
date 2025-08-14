package com.jotaempresas.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotaempresas.curso.entity.Order;


// somente isso , sera capaz de forma altomatica instancia um objeto do tipo repository com diveersas funções para manipular o banco
// ssa interface não precisa ser implementada
public interface OrderRepository extends JpaRepository<Order, Long>  {

}
