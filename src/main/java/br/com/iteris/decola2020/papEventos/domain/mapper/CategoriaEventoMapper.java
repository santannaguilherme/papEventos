package br.com.iteris.decola2020.papEventos.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.decola2020.papEventos.domain.dto.response.CategoriaEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;

@Component
public class CategoriaEventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public CategoriaEventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CategoriaEventoResponse toDto(CategoriaEvento input) {
        return mapper.
        map(input, CategoriaEventoResponse.class);
    }

} 