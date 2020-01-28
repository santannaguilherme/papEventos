package br.com.iteris.decola2020.papEventos.domain.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.iteris.decola2020.papEventos.configuration.MapperConfig;
import br.com.iteris.decola2020.papEventos.domain.dto.response.CategoriaEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;

/**
 * ClientMapperTest
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoriaEventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private CategoriaEventoMapper mapper;

    @Test
    public void shouldConvertEventoToEventoResponse() {
        CategoriaEvento entity = CategoriaEvento.builder().nomeCategoria("nomeCategoria").build();

        CategoriaEventoResponse dto = mapper.toDto(entity);

        assertEquals("Unexpected value found!", dto.getIdCategoriaEvento(), entity.getIdCategoriaEvento());
        assertEquals("Unexpected value found!", dto.getNomeCategoria(), entity.getNomeCategoria());
    }
}