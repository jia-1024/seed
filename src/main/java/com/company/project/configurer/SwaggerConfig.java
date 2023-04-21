package com.company.project.configurer;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author JHL
 * @version 1.0
 * @date 2023/4/21 15:15
 * @since : JDK 11
 */
@Configuration
@EnableKnife4j
public class SwaggerConfig {

    private static ApiInfo apiInfo;

    static {
        apiInfo = new ApiInfoBuilder()
                .title("后台接口文档 by JHL")
                .description("API接口文档")
                .termsOfServiceUrl(" ")
                .contact(new Contact("JHL", " ", "295672923@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台接口文档")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.project.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 如果要新增一个分组
    // @Bean
    // public Docket api2() {
    //     return new Docket(DocumentationType.SWAGGER_2)
    //             .groupName("api2")
    //             .apiInfo(apiInfo)
    //             .select()
    //             .apis(RequestHandlerSelectors.basePackage("com.hanliy.controller2"))
    //             .paths(PathSelectors.any())
    //             .build();
    // }
}