package com.ssafy.maytrip.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
//	Swagger-UI 3.x 확인
//	//http://localhost/vue/swagger-ui/index.html
	
	private final ServerProperties serverProperties;

    public SwaggerConfig(ServerProperties serverProperties) {
		super();
		this.serverProperties = serverProperties;
	}
    
	private String version = "V1";
    private String title = "MAYTRIP API " + version;
    private String controllerPackage = "com.ssafy.maytrip.controller";
    
    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
        	.produces(getProduceContentTypes())
            .groupName("ALL-API")
            .apiInfo(allApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket memberApi() {
    	return new Docket(DocumentationType.OAS_30)
    			.consumes(getConsumeContentTypes())
    			.produces(getProduceContentTypes())
    			.groupName("MEMBER-API")
    			.apiInfo(memberApiInfo())
    			.select()
    			.apis(RequestHandlerSelectors.basePackage(controllerPackage))
    			.paths(PathSelectors.ant("/api/member/**"))
    			.build()
    			.useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket boardApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .groupName("BOARD-API")
            .apiInfo(boardApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/api/boards/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket attractionApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .groupName("ATTRACTION-API")
            .apiInfo(attractionApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/api/attraction/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket CrewApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .groupName("Crew-API")
            .apiInfo(crewsApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/api/crews/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket SidoApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .groupName("Sido-API")
            .apiInfo(crewsApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/api/sido/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    @Bean
    public Docket GugunApi() {
        return new Docket(DocumentationType.OAS_30)
        	.consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .groupName("Gugun-API")
            .apiInfo(crewsApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(controllerPackage))
            .paths(PathSelectors.ant("/api/gugun/**"))
            .build()
            .useDefaultResponseMessages(false);
    }
    
    
    private ApiInfo allApiInfo() {
        Integer port = serverProperties.getPort();
        return new ApiInfoBuilder()
            .title(title+" Swagger Open API Docs")
            .description(
                "<h3>MayTrip API Reference for Developers</h3>Vuejs를 위한 API<br>")
            .version("0.1")
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//            .termsOfServiceUrl("http://edu.ssafy.com")
//            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo memberApiInfo() {
        return new ApiInfoBuilder()
            .title("사용자")
            .description("<h2>사용자 API</h2>")
            .version("0.1")
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//            .termsOfServiceUrl("http://edu.ssafy.com")
//            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo boardApiInfo() {
        return new ApiInfoBuilder()
            .title("게시판")
            .description("<h2>게시판 API</h2>")
            .version("0.1")
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//            .termsOfServiceUrl("http://edu.ssafy.com")
//            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo attractionApiInfo() {
        return new ApiInfoBuilder()
            .title("관광지")
            .description("<h2>관광지 API</h2>")
            .version("0.1")
//                .license("SSAFY License")
//                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//                .termsOfServiceUrl("http://edu.ssafy.com")
//                .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo crewsApiInfo() {
        return new ApiInfoBuilder()
            .title("크루")
            .description("<h2>크루 API</h2>")
            .version("0.1")
//                .license("SSAFY License")
//                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//                .termsOfServiceUrl("http://edu.ssafy.com")
//                .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo sidoApiInfo() {
        return new ApiInfoBuilder()
            .title("시도")
            .description("<h2>시도 API</h2>")
            .version("0.1")
//                .license("SSAFY License")
//                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//                .termsOfServiceUrl("http://edu.ssafy.com")
//                .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo gugunApiInfo() {
        return new ApiInfoBuilder()
            .title("구군")
            .description("<h2>구군 API</h2>")
            .version("0.1")
//                .license("SSAFY License")
//                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//                .termsOfServiceUrl("http://edu.ssafy.com")
//                .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    
	private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
//      consumes.add("application/xml;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
    

//	private String version = "V1";
//    private String title = "MAYTRIP API " + version;
//    
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
//                    .apiInfo(apiInfo()).groupName(version).select()
//                    .apis(RequestHandlerSelectors.basePackage("com.ssafy.ws.controller"))
//                    .paths(PathSelectors.any()).build()
//                    .useDefaultResponseMessages(false);
//    }
//    
//    private Set<String> getConsumeContentTypes() {
//        Set<String> consumes = new HashSet<>();
//        consumes.add("application/json;charset=UTF-8");
////      consumes.add("application/xml;charset=UTF-8");
//        consumes.add("application/x-www-form-urlencoded");
//        return consumes;
//    }
//
//    private Set<String> getProduceContentTypes() {
//        Set<String> produces = new HashSet<>();
//        produces.add("application/json;charset=UTF-8");
//        return produces;
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title(title)
//                .description("<h3>Maytrip API Reference for Developers</h3>Swagger를 이용한 maytrip API<br>") 
//                .contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
//                .license("SSAFY License")
//                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//                .version("1.0").build();
//    }
}
