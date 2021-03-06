package com.youyue.manege_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.youyue.framework.domain.cms")
@ComponentScan(basePackages = {"com.youyue.api","com.youyue.manege_cms","com.youyue.framework"})

public class ManegeCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManegeCmsApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
