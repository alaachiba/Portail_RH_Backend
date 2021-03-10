package com.smartup.p_rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication


public class PRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(PRhApplication.class, args);
	}


    /*private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
        		"Spring Boot Swagger Example API",
                "Spring Boot Swagger Example API for Youtube",
                "1.0",
                "Terms of Service",
                new Contact("TechPrimers", "https://www.youtube.com/TechPrimers",
                        "techprimerschannel@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html")
        );
        return apiInfo;
    }*/
}
