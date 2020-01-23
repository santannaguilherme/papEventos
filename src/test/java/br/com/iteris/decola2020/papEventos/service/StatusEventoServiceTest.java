package br.com.iteris.decola2020.papEventos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.repository.StatusEventoRepository;

/**
 * StatusEventoServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusEventoServiceTest {

 
    @Mock
    private StatusEventoRepository repositoryMock;

    @InjectMocks
    private StatusEventoService service;

    private final Integer IdEventoStatus = 1;
    private final String NomeStatus = "qqr coisa";
    
    StatusEvento entity = StatusEvento.builder()
    .idEventoStatus(IdEventoStatus)
    .nomeStatus(NomeStatus)
    .build();

    @Test
    public void should_listStatusEvents() {
        List<StatusEvento> list = new ArrayList<>();
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<StatusEvento> eventos = service.listStatusEvento();

        verify(repositoryMock, times(1)).findAll();
        assertNotNull("Array não deve ser nulo", eventos);
        assertEquals("Array deve ser de tamanho 1", 1, eventos.size());
    }

    @Test(expected = DataNotFoundException.class)
    public void should_ThrowDataNotFoundException_whenNotFound() {

        // given no data


        // when
        service.findById(1);
    }

    @Test
    public void should_findById() {

        // given
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        // when
        StatusEvento model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Client deve ser encontrado!", model);
    }
}