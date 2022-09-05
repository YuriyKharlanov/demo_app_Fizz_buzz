package com.kharlanov.demo_for_solveva;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("demo-public")
                .pathsToMatch("/demo/v1/**")
                .build();
    }
}
