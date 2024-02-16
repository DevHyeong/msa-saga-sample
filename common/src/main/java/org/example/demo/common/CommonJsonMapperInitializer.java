package org.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonJsonMapperInitializer {
    private final ObjectMapper objectMapper;

    public CommonJsonMapperInitializer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        registerMoneyModule();
    }
    public void registerMoneyModule(){
        objectMapper.registerModule(new MoneyModule());
    }
}
