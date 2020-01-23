
package br.com.iteris.decola2020.papEventos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.exception.DataCantBeDeletedException;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.exception.InvalidDateException;
import br.com.iteris.decola2020.papEventos.repository.EventoRepository;

/**
 * EventoServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class EventoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private EventoRepository repositoryMock;

    @InjectMocks
    private EventoService service;
    @Mock
    private StatusEventoService serviceStatus;

    private final String nome = "Nome";
    private final Date dataHoraInicio = new Date();
    private final Date dataHoraFim = new Date();
    private final String local = "Local";
    private final String descricao = "Qualquer coisa";
    private final Integer limiteVagas = 20;

    Evento entity = Evento.builder().descricao(descricao).nome(nome).dataHoraInicio(dataHoraInicio)
            .dataHoraFim(dataHoraFim).local(local).categoria(new CategoriaEvento()).status(new StatusEvento())
            .limiteVagas(limiteVagas).build();

    @Test
    public void should_create() {
        when(repositoryMock.save(entity)).thenReturn(entity);
        Calendar cal = Calendar.getInstance();
        entity.getDataHoraInicio().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));
        entity.getDataHoraFim().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000) + 30000);
        Evento e = service.createEvento(entity);
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }

    @Test
    public void should_throwDataInvalidaException() {
        Calendar cal = Calendar.getInstance();
        entity.getDataHoraInicio().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));
        entity.getDataHoraFim().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));

        expected.expect(InvalidDateException.class);

        service.createEvento(entity);
    }

    @Test
    public void should_listEvents() {
        List<Evento> list = new ArrayList<>();
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<Evento> eventos = service.listEvento();

        verify(repositoryMock, times(1)).findAll();
        assertNotNull("Array não deve ser nulo", eventos);
        assertEquals("Array deve ser de tamanho 1", 1, eventos.size());
    }

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {

        // given no data

        // then
        expected.expect(DataNotFoundException.class);

        // when
        service.findById(1);
    }

    @Test
    public void should_findById() {

        // given
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        // when
        Evento model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Client deve ser encontrado!", model);
    }

    @Test
    public void should_cancelEvento() {
        should_findById();
        dataHoraInicio.setTime(dataHoraInicio.getTime() + 24 * 60 * 60 * 1000);
        entity.setDataHoraInicio(dataHoraInicio);

        // given
        when(repositoryMock.save(entity)).thenReturn(entity);

        // when
        Evento model = service.cancelEvento(anyInt());

        assertEquals("A data deve ser diferente", model, entity);
    }

    @Test
    public void should_throwDataInvalidaCancel() {
        should_findById();
        dataHoraInicio.setTime(dataHoraInicio.getTime());
        entity.setDataHoraInicio(dataHoraInicio);

        expected.expect(InvalidDateException.class);

       service.cancelEvento(anyInt());
    }

    @Test
    public void should_updateEvento() {
        should_findById();
        when(repositoryMock.save(entity)).thenReturn(entity);
        Calendar cal = Calendar.getInstance();
        entity.getDataHoraInicio().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));
        entity.getDataHoraFim().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000) + 30000);
        Evento e = service.updateEvento(entity, anyInt());
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }

    @Test
    public void should_notUpdateEvento() {
        should_findById();
        Calendar cal = Calendar.getInstance();
        entity.getDataHoraInicio().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));
        entity.getDataHoraFim().setTime(cal.getTimeInMillis() + (24 * 60 * 60 * 1000));

        expected.expect(InvalidDateException.class);

        service.updateEvento(entity, anyInt());
    }

    @Test
    public void should_deleteEvento() {
        should_findById();

        service.deletEvento(anyInt());
    }

    @Test(expected = DataCantBeDeletedException.class)
    public void should_deleteException() {
        should_findById();
        doThrow(new InvalidDateException("Error occurred"))
        .when(repositoryMock)
        .deleteById(anyInt());

        service.deletEvento(1);

        expected.expect(DataCantBeDeletedException.class);
    }

}