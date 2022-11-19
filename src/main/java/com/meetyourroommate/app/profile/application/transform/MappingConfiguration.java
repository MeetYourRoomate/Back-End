package com.meetyourroommate.app.profile.application.transform;


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
}
