package com.xmu.wowoto.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author MedalWill
 * @date 2019/12/14 21:13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xmu.wowoto.aftersale.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("wowomall-AfterSale接口文档")
                .description("wowoto! one yuan for four! hey hey!")
                .version("1.0")
                .build();
    }
}
