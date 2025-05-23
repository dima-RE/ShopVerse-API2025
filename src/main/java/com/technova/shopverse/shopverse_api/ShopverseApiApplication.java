package com.technova.shopverse.shopverse_api;

import com.technova.shopverse.shopverse_api.model.Category;
import com.technova.shopverse.shopverse_api.model.Product;
import com.technova.shopverse.shopverse_api.repositories.CategoryRepository;
import com.technova.shopverse.shopverse_api.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopverseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopverseApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ProductRepository prodr, CategoryRepository catr) {
		return args -> {
			System.out.println("=== Se comienza sin datos para realizar pruebas HTTPCODE ===");
			// ===== CARGA INICIAL =====
			Category testCat = new Category("Tecnología", "Productos electrónicos y computación");
			catr.save(testCat);
			catr.save(new Category("Hogar", "Artículos para el hogar y decoración"));
			catr.save(new Category("Indumentaria", "Ropa y accesorios"));
			prodr.save(new Product("Laptop Lenovo", "Notebook 15 pulgadas", 850.0, testCat));
			prodr.save(new Product("Mouse Logitech", "Mouse inalámbrico", 25.5, testCat));
			prodr.save(new Product("Monitor Samsung", "Monitor 24 pulgadas", 199.99,testCat));

			// ===== DATE =====
			/*LocalDate dateNow = LocalDate.now();
			LocalDate dateTom = dateNow.plusDays(1);
			LocalDate dateFu1 = dateNow.plusYears(5);
			LocalDate dateFu2 = dateTom.plusYears(6);*/

			// Añadir Spring Boot Starter Security
		};
	}

}
