package br.com.projeto.product.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import br.com.projeto.product.modelo.Product;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.projeto.product"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(infoApi())
				.ignoredParameterTypes(Product.class)
				.directModelSubstitute(Pageable.class, SwaggerPageable.class);

	}
	
    private ApiInfo infoApi() {

        ApiInfo infoApi = new ApiInfoBuilder()
                .title ("Product - API Documentation")
                .description ("CRUD Products")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .contact(new Contact("Ramildo Pereira da Silva Junior",
                		"https://github.com/Juninho013/Compass-CRUD", "rpsjuninho@gmail.com"))
                .build();

        return infoApi;
    }
}
