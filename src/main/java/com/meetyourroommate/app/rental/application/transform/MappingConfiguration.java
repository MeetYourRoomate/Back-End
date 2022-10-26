package com.meetyourroommate.app.rental.application.transform;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("RentalMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public RentalOfferMapper rentalOfferMapper() { return new RentalOfferMapper(); }


}
