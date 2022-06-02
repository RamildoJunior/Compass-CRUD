package br.com.projeto.product.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.product.controller.form.ProductForm;
import br.com.projeto.product.controller.service.ProductService;
import br.com.projeto.product.dto.ProductDto;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/products")
public class ProductsController {

	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public Page<ProductDto> list(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return productService.list(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(
			@ApiParam(value = "Id of product to return", required = true, example = "123") @PathVariable Long id) {
		return productService.findById(id);
	}

	@GetMapping("/search")
	Page<ProductDto> search(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) Double max_price, @RequestParam(required = false) Double min_price,
			@RequestParam(required = false) String q) {
		return productService.search(pageable, max_price, min_price, q);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		return productService.create(form, uriBuilder);
	}

	@Transactional
	@PutMapping("/{id}")

	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		return productService.update(id, form);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
		return productService.delete(id);
	}

}
