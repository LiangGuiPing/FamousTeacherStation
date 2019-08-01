package com.api.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Value("${swagger.title}")
    String SWAGGER_TITLE;

    @Value("${swagger.path}")
    String SWAGGER_PATH;

    @Value("${swagger.description}")
    String SWAGGER_DESCRIPTION;

    @Value("${swagger.service.url}")
    String SWAGGER_SERVICE_URL;

    @Value("${swagger.version}")
    String SWAGGER_VERSION;

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_PATH))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()).forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .termsOfServiceUrl(SWAGGER_SERVICE_URL)
                .version(SWAGGER_VERSION).build();
    }

}
