package com.jotaempresas.curso.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_pagamentos")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// atrubutos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="MOMENTO")
	private Instant momente;	
	
	// associação compagamento
	@OneToOne
	@MapsId                
	private Order order;  
	 /* ( mapeamento de um para um como mesmo ID) na classe dependente , no caso pagamento, tem que ter essas anotações caso seja relacionamento um para um*/
	
	
	// construtores
	public Payment() {
		
	}

	public Payment(Long id, Instant momente, Order order) {
		super();
		this.id = id;
		this.momente = momente;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomente() {
		return momente;
	}

	public void setMomente(Instant momente) {
		this.momente = momente;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order);
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", momente=" + momente + ", order=" + order + "]";
	}
	
	

}
