package org.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public CommonJsonMapperInitializer commonJsonMapperInitializer(ObjectMapper objectMapper){
        return new CommonJsonMapperInitializer(objectMapper);
    }
}
