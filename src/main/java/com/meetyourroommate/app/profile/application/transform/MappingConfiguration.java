package com.meetyourroommate.app.profile.application.transform;


import com.meetyourroommate.app.profile.application.transform.resources.AtributeResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ProfileMapper profileMapper() { return new ProfileMapper(); }

    @Bean
    public ProfileDtoMapper profileDtoMapper() { return new ProfileDtoMapper(); }

    @Bean
    public AtributeDtoMapper atributeDtoMapper() { return new AtributeDtoMapper(); }

    @Bean
    public AtributeResourceMapper atributeResource() { return new AtributeResourceMapper(); }
}
