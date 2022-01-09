package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationPropertiesBindHandlerAdvisor;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomBindHandlerAdvisor implements ConfigurationPropertiesBindHandlerAdvisor {
    
    @Override
    public BindHandler apply(BindHandler bindHandler) {
        return new CustomBindHandler(bindHandler);
    }

}