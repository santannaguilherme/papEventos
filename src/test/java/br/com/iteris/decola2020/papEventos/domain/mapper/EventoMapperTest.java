package br.com.iteris.decola2020.papEventos.domain.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.iteris.decola2020.papEventos.configuration.MapperConfig;
import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoUpdateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.EventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;

/**
 * ClientMapperTest
 */
@RunWith(MockitoJUnitRunner.class)
public class EventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private EventoMapper mapper;

    @Test
    public void shouldConvertEventoToEventoResponse() {
        Date data = new Date();
        Evento entity = Evento.builder().descricao("descricao").nome("nome").dataHoraInicio(data).dataHoraFim(data)
                .local("local").categoria(new CategoriaEvento()).status(new StatusEvento()).limiteVagas(1).build();

        EventoResponse dto = mapper.toDto(entity);

        assertEquals("Unexpected value found!", dto.getIdEvento(), entity.getIdEvento());
        assertEquals("Unexpected value found!", dto.getDescricao(), entity.getDescricao());
        assertEquals("Unexpected value found!", dto.getNome(), entity.getNome());
        assertEquals("Unexpected value found!", dto.getDataHoraFim(), entity.getDataHoraFim());
        assertEquals("Unexpected value found!", dto.getDataHoraInicio(), entity.getDataHoraInicio());
        assertEquals("Unexpected value found!", dto.getCategoria(), entity.getCategoria());
        assertEquals("Unexpected value found!", dto.getStatus(), entity.getStatus());
        assertEquals("Unexpected value found!", dto.getLocal(), entity.getLocal());
    }

    @Test
    public void shouldConvertEventoCreateRequestToEvento() {
        Date data = new Date();
        EventoCreateRequest dto = EventoCreateRequest.builder().descricao("descricao").nome("nome").dataHoraInicio(data)
                .dataHoraFim(data).local("local").idCategoriaEvento(1).limiteVagas(1).build();

        Evento entity = mapper.fromDto(dto);

        assertEquals("Unexpected value found!", dto.getDescricao(), entity.getDescricao());
        assertEquals("Unexpected value found!", dto.getNome(), entity.getNome());
        assertEquals("Unexpected value found!", dto.getDataHoraFim(), entity.getDataHoraFim());
        assertEquals("Unexpected value found!", dto.getDataHoraInicio(), entity.getDataHoraInicio());
        assertEquals("Unexpected value found!", dto.getLocal(), entity.getLocal());
    }

    @Test
    public void shouldConvertEventoUpdateRequestToEvento() {
        Date data = new Date();
        EventoUpdateRequest dto = EventoUpdateRequest.builder().descricao("descricao").nome("nome").dataHoraInicio(data)
                .dataHoraFim(data).local("local").idCategoriaEvento(1).idEventoStatus(1).limiteVagas(1).build();

        Evento entity = mapper.updateFromDto(dto, 1);

        assertEquals("Unexpected value found!", dto.getDescricao(), entity.getDescricao());
        assertEquals("Unexpected value found!", dto.getNome(), entity.getNome());
        assertEquals("Unexpected value found!", dto.getDataHoraFim(), entity.getDataHoraFim());
        assertEquals("Unexpected value found!", dto.getDataHoraInicio(), entity.getDataHoraInicio());
        assertEquals("Unexpected value found!", dto.getLocal(), entity.getLocal());
    }
}