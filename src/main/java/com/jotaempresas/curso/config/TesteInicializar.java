package com.jotaempresas.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jotaempresas.curso.entity.Category;
import com.jotaempresas.curso.entity.Order;
import com.jotaempresas.curso.entity.Product;
import com.jotaempresas.curso.entity.User;
import com.jotaempresas.curso.entity.enums.OrderStatus;
import com.jotaempresas.curso.repositories.CategoryRepository;
import com.jotaempresas.curso.repositories.OrderRepository;
import com.jotaempresas.curso.repositories.ProductRepository;
import com.jotaempresas.curso.repositories.UserRepository;

// Essa anotação @Component indica que o Spring deve gerenciar esta classe como um Bean (componente da aplicação)
// Assim, será possível fazer injeção de dependência desta classe em outros lugares.
@Component
public class TesteInicializar {

	// Injetamos o UserRepository para que possamos acessar os métodos de
	// persistência no banco de dados (save, findAll, etc)
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	// Criamos dois objetos User com dados fictícios para popular o banco de dados
	// O 'null' é usado no ID pois o banco de dados (através do JPA) irá gerar
	// automaticamente o ID

	// USUARIOS
	User u1 = new User(null, "Maria Brown II", "maria@gmail.com", "988888888", "123457");
	User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

	// ORDENS
	Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
	Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.DELIVERED);
	Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

	// CATEGORIA
	Category c1 = new Category(null, "Sala");
	Category c2 = new Category(null, "Cozinha");
	Category c3 = new Category(null, "Banheiro");
	Category c4 = new Category(null, "Cama");

	// PRODUTOS
	Product p1 = new Product(null, "Notebook Dell Inspiron", "Notebook 15'' i7, 16GB RAM, SSD 512GB", 4500.00,
			"https://exemplo.com/imagens/notebook-dell.jpg", c1);
	Product p2 = new Product(null, "Smartphone Samsung Galaxy S23", "Tela 6.1'', 8GB RAM, 256GB", 3999.00,
			"https://exemplo.com/imagens/galaxy-s23.jpg", c2);
	Product p3 = new Product(null, "Smart TV LG 55'' 4K", "Painel OLED, HDR, WebOS", 3500.00,
			"https://exemplo.com/imagens/tv-lg-oled.jpg", c3);
	Product p4 = new Product(null, "Mouse Gamer Logitech G502", "RGB, 25.600 DPI, 11 botões", 350.00,
			"https://exemplo.com/imagens/mouse-logitech.jpg", c4);
	Product p5 = new Product(null, "Cadeira Gamer ThunderX3", "Reclinável, apoio de braço 4D", 1200.00,
			"https://exemplo.com/imagens/cadeira-gamer.jpg", c1);
	

	public void insertUserAuto() {
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

	public void insertOrderAuto() {
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

	public void insertCategory() {
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
	}

	public void insertProduct() {
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
	}
}
