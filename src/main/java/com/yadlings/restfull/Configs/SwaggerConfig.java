package com.yadlings.restfull.Configs;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApiV1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Version 1.0.0")
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfov1())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/v1/poll.*"))
				.build();
	}
	@Bean
	public Docket postsApiV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.groupName("Version 1.0.1")
				.apiInfo(apiInfov2())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/v2/poll.*"))
				.build();
	}
	@Bean
	public Docket postsApiV3() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.groupName("Version 1.0.2")
				.apiInfo(apiInfov3())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/v3/poll.*"))
				.build();
	}
	private ApiInfo apiInfov1() {
		return new ApiInfoBuilder()
				.title("QuickPoll Rest API")
				.description("QuickPoll Rest-full APi for creating and managing polls")
				.contact(new Contact("NIndaba Arthur","yadlings.com","arthurninda@gmail.com"))
				.license("Yadlings license")
				.version("1.0.0")
				.build();
	}
	private ApiInfo apiInfov2() {
		return new ApiInfoBuilder()
				.title("QuickPoll Rest API")
				.description("QuickPoll Rest-full APi for creating and managing polls")
				.contact(new Contact("NIndaba Arthur","yadlings.com","arthurninda@gmail.com"))
				.license("Yadlings license")
				.version("1.0.1")
				.build();
	}
	private ApiInfo apiInfov3() {
		return new ApiInfoBuilder()
				.title("QuickPoll Rest API")
				.description("QuickPoll Rest-full APi for creating and managing polls")
				.contact(new Contact("NIndaba Arthur","yadlings.com","arthurninda@gmail.com"))
				.license("Yadlings license")
				.version("1.0.2")
				.build();
	}

}