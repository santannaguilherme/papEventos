package br.com.iteris.decola2020.papEventos.domain.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.iteris.decola2020.papEventos.configuration.MapperConfig;
import br.com.iteris.decola2020.papEventos.domain.dto.request.ParticipacaoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.request.ParticipacaoUpdateAvaliacaoRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.ParticipacaoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.service.EventoService;

/**
 * ClientMapperTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ParticipacaoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private ParticipacaoMapper mapper;

    @Mock
	private EventoService eventoService;;

    @Test
    public void shouldConvertEventoToEventoResponse() {
        Participacao entity = Participacao.builder().loginParticipante("loginParticipante").evento(new Evento()).nota(0).flagPresente(false).build();

        ParticipacaoResponse dto = mapper.toDto(entity);

        assertEquals("Unexpected value found!", dto.getIdParticipacao(), entity.getIdParticipacao());
        assertEquals("Unexpected value found!", dto.getLoginParticipante(), entity.getLoginParticipante());
        assertEquals("Unexpected value found!", dto.getNota(), entity.getNota());
        assertEquals("Unexpected value found!", dto.getEvento(), entity.getEvento());
        assertEquals("Unexpected value found!", dto.getFlagPresente(), entity.getFlagPresente());
    }

    @Test
    public void shouldConvertEventoCreateRequestToEvento() {
        ParticipacaoCreateRequest dto = ParticipacaoCreateRequest.builder().loginParticipante("loginParticipante").build();

        Participacao entity = mapper.newFromDto(dto);

        assertEquals("Unexpected value found!", dto.getLoginParticipante(), entity.getLoginParticipante());
    }

    @Test
    public void shouldConvertEventoUpdateRequestToEvento() {
        ParticipacaoUpdateAvaliacaoRequest dto = ParticipacaoUpdateAvaliacaoRequest.builder().nota(1).comentario("comentario").build();

        Participacao entity = mapper.rateFromDto(dto);

        assertEquals("Unexpected value found!", dto.getNota(), entity.getNota());
        assertEquals("Unexpected value found!", dto.getComentario(), entity.getComentario());
    }

    
}