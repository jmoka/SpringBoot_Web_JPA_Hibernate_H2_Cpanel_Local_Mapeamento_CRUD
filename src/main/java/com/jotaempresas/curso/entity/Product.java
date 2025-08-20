package com.jotaempresas.curso.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NOME")
	private String name;
	@Column(name = "DESCRICAO")
	private String desciption;
	@Column(name = "PRECO")
	private Double price;
	@Column(name = "URL_IMAGEM")
	private String imgUrl;

	// ASSOCIAÇOES
	@ManyToOne
	@JoinColumn(name= "Key_Category_Id")
	private Category category;
	
	// associação com os OrdemItem
	@OneToMany(mappedBy = "Key_Order_Product.product")	
	private Set<OrderItem> itens = new HashSet<>();

	// CONSTRUTORES
	public Product() {

	}

	public Product(Long id, String name, String desciption, Double price, String imgUrl, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.price = price;
		this.imgUrl = imgUrl;
		this.category = category;
	}
	
		
	@JsonIgnore
	public Set<Order> getOrders() {
		// crio uma coleção de Order pedidos
		Set<Order> set = new HashSet<>();
		
		for (OrderItem i : itens) { // varre a lista de itens e para cada item 
			set.add(i.getOrder()); // adciono em cada ordem
		}
		return set;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desciption=" + desciption + ", price=" + price + ", imgUrl="
				+ imgUrl + "]";
	}

}
