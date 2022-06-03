package br.com.projeto.product.config.swagger;

import org.springframework.lang.Nullable;

import io.swagger.annotations.ApiParam;

public class SwaggerPageable {

	@ApiParam(value = "Page to load", example = "0")
	@Nullable
	private Integer page;

	@ApiParam(value = "Quantity of products", example = "5")
	@Nullable
	private Integer size;

	@ApiParam(value = "Ordering of products")
	@Nullable
	private String sort;

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public String getSort() {
		return sort;
	}

}
