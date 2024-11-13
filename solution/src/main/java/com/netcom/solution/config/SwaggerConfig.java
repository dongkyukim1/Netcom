package com.netcom.solution.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("넷컴 ERP API 문서")
                        .version("v1")
                        .description("넷컴 ERP 시스템의 API 문서입니다."));
    }
}
