package com.shou.socialpracticemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("SocialPracticeManager 接口文档")
                .description("登录请求格式举例：POST http://localhost:8080/api/login?username=pwb&password=pwb")
                .version("2.0")
                .build();

        docket.apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shou.socialpracticemanager.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
}

