package com.ealbarran.DemoTaskboardApp.Context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
/*
La anotación Configuration indica que la clase anotada puede crear bines que formarán parte del aplication context
*/
@EnableSwagger2
/*
Habilitando Swagger 2
*/
public class SwaggerConfig {

    @Bean
    /*
    Creando el bean principal con la información asociada de la API
    */

    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ealbarran.DemoTaskboardApp.Controller"))
                /*
                Base package debe contener el nombre exacto del proyecto, Swagger escanea todo el paquete controller en busca de anotaciones Rest Controller para renderizar métodos y documentar la API
                */
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {

        return new ApiInfo(

                "TaskboardApp API",
                "API REST Taskboard App",
                "v1",
                "Terms of Service",
                new Contact("EnriqueAlbarrán", "www.url.com", "ealbarran2005.10@gmail.com"),
                "License of API", "API License URL", Collections.emptyList());

    }
}
