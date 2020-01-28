package br.com.iteris.decola2020.papEventos.domain.mapper;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoUpdateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.EventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;

@Component
public class EventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public EventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventoResponse toDto(Evento entity) {
        return mapper.map(entity, EventoResponse.class);
    }

    public Evento fromDto(EventoCreateRequest dto) {
        return mapper.map(dto, Evento.class);
    }

    public Evento updateFromDto(@Valid EventoUpdateRequest model, Integer id) {
        Evento e = mapper.map(model, Evento.class);
        e.setIdEvento(id);
        return e;
    }

}