package br.com.iteris.decola2020.papEventos.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.decola2020.papEventos.domain.dto.request.ParticipacaoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.request.ParticipacaoUpdateAvaliacaoRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.ParticipacaoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.service.EventoService;

@Component
public class ParticipacaoMapper {

    private final ModelMapper mapper;    
	private final EventoService eventoService;

    @Autowired
    public ParticipacaoMapper(ModelMapper mapper,EventoService eventoService) {
        this.mapper = mapper;
		this.eventoService = eventoService;
    }

    public ParticipacaoResponse toDto(Participacao input) {
        return mapper.map(input, ParticipacaoResponse.class);
    }

    public Participacao fromDto(ParticipacaoCreateRequest input) {
        return mapper.map(input, Participacao.class);
    }

    public Participacao updateFromDto(ParticipacaoCreateRequest input,Integer id) {
        Participacao p = mapper.map(input, Participacao.class);
        p.setIdParticipacao(id);
        return p;
    }

    public Participacao newFromDto(ParticipacaoCreateRequest input) {
        Participacao p = mapper.map(input, Participacao.class);
        p.setEvento(eventoService.findById(input.getIdEvento()));
        p.setComentario("");
        p.setFlagPresente(false);
        p.setNota(0);
        return p;
    }
    
    public Participacao rateFromDto(ParticipacaoUpdateAvaliacaoRequest input) {
        Participacao p = mapper.map(input, Participacao.class);
        return p;
    }

}