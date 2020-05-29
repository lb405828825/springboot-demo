package com.xh.demo_lb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/25
 */
@EnableSwagger2 //声明启动Swagger2
@Configuration //声明该类为配置类
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerPlugins() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xh.demo_lb.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Health API Document")
                .description("Health")
                .version("0.0.1")
                .build();
    }
}
