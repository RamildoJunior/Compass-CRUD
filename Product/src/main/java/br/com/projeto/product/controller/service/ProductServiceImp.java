package br.com.projeto.product.controller.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.product.controller.form.ProductForm;
import br.com.projeto.product.dto.ProductDto;
import br.com.projeto.product.modelo.Product;
import br.com.projeto.product.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Page<ProductDto> list(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		return ProductDto.converter(products);

	}

	@Override
	public ResponseEntity<ProductDto> findById(Long id) {
		Product product = productRepository.getReferenceById(id);
		return ResponseEntity.ok(new ProductDto(product));

	}

	@Override
	public Page<ProductDto> search(Pageable pageable, Double max_price, Double min_price, String q) {
		Page<Product> product = productRepository.findByPrice(pageable, max_price, min_price, q);
		return ProductDto.converter(product);
	}

	@Override
	public ResponseEntity<ProductDto> create(ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.converter();
		productRepository.save(product);
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}

	@Override
	public ResponseEntity<ProductDto> update(Long id, ProductForm form) {
		Product product = form.atualizar(id, productRepository);
		return ResponseEntity.ok(new ProductDto(product));

	}

	@Override
	public ResponseEntity<ProductDto> delete(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		productRepository.deleteById(id);
		return ResponseEntity.ok(new ProductDto(optional.get()));
	}

}
