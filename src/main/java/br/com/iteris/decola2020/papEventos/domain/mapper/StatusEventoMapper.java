package br.com.iteris.decola2020.papEventos.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.decola2020.papEventos.domain.dto.response.StatusEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;

@Component
public class StatusEventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public StatusEventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StatusEventoResponse toDto(StatusEvento input) {
        return mapper.
        map(input, StatusEventoResponse.class);
    }

} 