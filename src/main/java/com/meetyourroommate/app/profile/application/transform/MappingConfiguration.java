package com.meetyourroommate.app.profile.application.transform;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ProfileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ProfileMapper profileMapper() { return new ProfileMapper(); }

}
