package com.meetyourroommate.app.property.application.transform;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("PropertyMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PropertyMapper propertyMapper() { return new PropertyMapper(); }
    @Bean
    public PropertyAssetsMapper propertyAssetsMapper() { return new PropertyAssetsMapper(); }


}
