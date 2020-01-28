package br.com.iteris.decola2020.papEventos.domain.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.iteris.decola2020.papEventos.configuration.MapperConfig;
import br.com.iteris.decola2020.papEventos.domain.dto.response.StatusEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;

/**
 * ClientMapperTest
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusEventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private StatusEventoMapper mapper;

    @Test
    public void shouldConvertEventoToEventoResponse() {
        StatusEvento entity = StatusEvento.builder().nomeStatus("nomeStatus").build();

        StatusEventoResponse dto = mapper.toDto(entity);

        assertEquals("Unexpected value found!", dto.getIdEventoStatus(), entity.getIdEventoStatus());
        assertEquals("Unexpected value found!", dto.getNomeStatus(), entity.getNomeStatus());
    }
}