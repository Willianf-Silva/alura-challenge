package br.com.wnfa.alurachallenge.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.wnfa.alurachallenge.resource"))
                .paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/lancamentos/*")) //Essa parte do código define os endpoint que serão apresentados no swagger-ui
                .build()
                .apiInfo(apiInfo())
                .tags(
                		new Tag("Usuários", "Gerenciamento dos usuários"),
                		new Tag("Receitas", "Gerenciamento das receitas"),
                		new Tag("Despesas", "Gerenciamento das despesas"),
                		new Tag("Resumo", "Visão geral das receitas e despesas")
                );
    }


    /*
     * construindo as informações da API para aparecer no swagger-ui
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Alura Challenge")
                .description("API para controle financeiro.")
                .version("1")
                .contact(new Contact("Willian Silva", "https://www.linkedin.com/in/willianferreirasilva/", "willian.ferreira.da.silva@gmail.com"))
                .build();
    }
}
