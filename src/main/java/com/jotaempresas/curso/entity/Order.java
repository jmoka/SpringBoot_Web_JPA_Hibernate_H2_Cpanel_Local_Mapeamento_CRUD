package com.jotaempresas.curso.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.jotaempresas.curso.entity.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order") // usar sempre tb_ para não dar conflito
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "MOMENT")
	private Instant moment;
	@Column(name = "ORDER_STATUS")
	private int orderStatus;
	
	
	// Para cada order existe um cliente 	
	
	@ManyToOne    // anotação onde informar que essa chave estranjeira de user, onde são muitas ordens para um user
	@JoinColumn(name= "Key_User_Id") // nome da chave estranjeira no banco
	private User user; 
	
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, User user, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.moment = moment;
		this.user = user;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getCliente() {
		return user;
	}

	public void setCliente(User user) {
		this.user = user;
	}
	
	

	public OrderStatus getOrderStatus() {
		return OrderStatus.enumOrder(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus !=null) {
			this.orderStatus = orderStatus.getCode();			
		}
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", moment=" + moment + ", orderStatus=" + orderStatus + ", user=" + user + "]";
	}

	
	
	
	
}
