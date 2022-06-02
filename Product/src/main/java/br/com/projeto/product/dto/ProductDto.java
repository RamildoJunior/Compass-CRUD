package br.com.projeto.product.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.projeto.product.modelo.Product;

public class ProductDto {

	private Long id;

	private String name;

	private String description;

	private double price;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
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

	public static List<ProductDto> converterLista(List<Product> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());

	}

	public static Page<ProductDto> converter(Page<Product> products) {
		return products.map(ProductDto::new);
	}

}
