package br.com.projeto.product.controller.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.product.controller.form.ProductForm;
import br.com.projeto.product.dto.ProductDto;

public interface ProductService {

	Page<ProductDto> list(Pageable pageable);

	ResponseEntity<ProductDto> findById(Long id);

	Page<ProductDto> search(Pageable pageable, Double max_price, Double min_price, String q);

	ResponseEntity<ProductDto> create(ProductForm form, UriComponentsBuilder uriBuilder);

	ResponseEntity<ProductDto> update(Long id, ProductForm form);

	ResponseEntity<ProductDto> delete(Long id);
}
