package com.jotaempresas.curso.entity;

import java.io.Serializable;
import java.util.Objects;

import com.jotaempresas.curso.entity.pk.OrderItemPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_orderItem")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@Column(name="Key_Order_Product")
	private OrderItemPK Key_Order_Product; // ira criar no banco de dados um campo correspondente Par_Order_Product
	
	@Column(name="QUANTIDADE")
	private Integer quantity;
	@Column(name="PECO")
	private Double price;
	
	
	public OrderItem() {
		
	}


	public OrderItem(Order order, Product product, Integer quantity, Double price) { // aqui no construtor precisamos incluir o produto e o pedido
		super();
		this.Key_Order_Product = new OrderItemPK(); // inicialização obrigatória
		Key_Order_Product.setOrder(order);
		Key_Order_Product.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
		
	

	public Order getOrder() {
		return Key_Order_Product.getOrder();
	}
	
	public void setOrder(Order order) {
		Key_Order_Product.setOrder(order);
	}
	
	
	public Product getProduct() {
		return Key_Order_Product.getProduct();
	}
	
	public void setProduct(Product Product) {
		Key_Order_Product.setProduct(Product);
	}
	


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Double getPrive() {
		return price;
	}


	public void setPrive(Double price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Key_Order_Product);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(Key_Order_Product, other.Key_Order_Product);
	}
	
	

}
