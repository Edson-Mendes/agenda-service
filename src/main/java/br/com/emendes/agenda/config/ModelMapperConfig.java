package br.com.emendes.agenda.config;

import br.com.emendes.agenda.api.request.AgendaRequest;
import br.com.emendes.agenda.domain.entity.Agenda;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    PropertyMap<AgendaRequest, Agenda> personMap = new PropertyMap<>() {
      protected void configure() {
        map().setId(null);
      }
    };

    mapper.addMappings(personMap);
    return mapper;
  }

}
