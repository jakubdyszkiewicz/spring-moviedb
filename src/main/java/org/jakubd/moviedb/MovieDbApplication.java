package org.jakubd.moviedb;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MovieDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDbApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("moviedb")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build();
	}

}
