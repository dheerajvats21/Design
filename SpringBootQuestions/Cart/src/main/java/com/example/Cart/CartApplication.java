package com.example.Cart;

import com.example.Cart.entities.Item;
import com.example.Cart.entities.User;
import com.example.Cart.repos.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartApplication implements CommandLineRunner {

	@Autowired
	DataBase db;

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		db.addItem(Item.builder()
						.id("1")
				.name("name1")
				.price(20)
				.build());
		db.addItem(Item.builder()
				.id("2")
				.name("name2")
				.price(100)
				.build());
		db.addUser(User.builder()
				.id("1")
				.age(23)
				.name("A")
				.build());
		db.addUser(User.builder()
				.id("2")
				.age(30)
				.name("B")
				.build());
	}
}
