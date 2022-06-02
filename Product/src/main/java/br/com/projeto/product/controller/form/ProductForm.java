package br.com.projeto.product.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.projeto.product.modelo.Product;
import br.com.projeto.product.repository.ProductRepository;

public class ProductForm {

	@NotBlank

	private String name;

	@NotBlank

	private String description;

	@Positive
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product converter() {
		return new Product(name, description, price);
	}

	public Product atualizar(Long id, ProductRepository productRepository) {
		Product product = productRepository.getReferenceById(id);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setDescription(this.description);
		return product;
	}

}
