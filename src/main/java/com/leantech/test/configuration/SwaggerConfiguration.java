package com.leantech.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Api info.
     *
     * @return ApiInfo
     */
    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Test API for LEAN TECH")
                .description("REST API developed to evaluate technical skills")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("")
                .version("0.0.1")
                .build();
    }

    /**
     * Custom docket implementation
     *
     * @return docket
     */
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.leantech.test"))
                .build()
                .apiInfo(apiInfo());
    }

}