package com.meetyourroommate.app.shared.application.transform;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {

  @Bean
  public EnhancedModelMapper modelMapper() {
    return new EnhancedModelMapper();
  }
}
