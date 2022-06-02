package br.com.projeto.product.config.swagger;

import org.springframework.lang.Nullable;

import io.swagger.annotations.ApiParam;

public class SwaggerPageable {

	@ApiParam(value = "Pagina a ser carregada", example = "0")
	@Nullable
	private Integer page;

	@ApiParam(value = "Quantidade de produtos", example = "5")
	@Nullable
	private Integer size;

	@ApiParam(value = "Ordenação dos produtos")
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
