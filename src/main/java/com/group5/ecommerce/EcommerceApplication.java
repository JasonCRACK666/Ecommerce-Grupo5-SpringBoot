package com.group5.ecommerce;

import com.group5.ecommerce.entity.*;
import com.group5.ecommerce.entity.enums.Role;
import com.group5.ecommerce.repository.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class EcommerceApplication {

	@Value("${admin.username}")
	private String adminUsername;

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CategoryRepository categoryRepository,
			UserRepository userRepository,
			AccountRepository accountRepository,
			CartRepository cartRepository,
			WishListRepository wishListRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			var account = new Account();
			accountRepository.save(account);

			var cart = new Cart();
			cartRepository.save(cart);

			var wishList = new WishList();
			wishListRepository.save(wishList);

			var user = User.builder()
					.userName(this.adminUsername)
					.role(Role.ADMIN)
					.email(this.adminEmail)
					.firstName("Admin")
					.lastName("Ecommerce")
					.password(passwordEncoder.encode(this.adminPassword))
					.isActive(true)
					.activateCode(UUID.randomUUID())
					.DNI(12345678)
					.account(account)
					.cart(cart)
					.wishList(wishList)
					.build();

			userRepository.save(user);

			Category computadoras = Category.builder()
					.name("computadoras")
					.build();

			Category laptops = Category.builder()
					.name("laptops")
					.build();

			Category celulares = Category.builder()
					.name("celulares")
					.build();

			categoryRepository.save(computadoras);
			categoryRepository.save(laptops);
			categoryRepository.save(celulares);
		};
	}

}
