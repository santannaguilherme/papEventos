
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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.repository.ParticipacaoRepository;

/**
 * EventoServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ParticipacaoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private ParticipacaoRepository repositoryMock;

    @InjectMocks
    private ParticipacaoService service;

    private final String loginParticipante = "qqr coisa"; 
    private final Boolean flagPresente = false;
    private final Integer nota = 1;
    private final String comentario = "qqr coisa";

    Participacao entity = Participacao.builder().LoginParticipante(loginParticipante)
    .FlagPresente(flagPresente)
    .Nota(nota)
    .Comentario(comentario)
    .evento(new Evento())
    .build();

    @Test
    public void should_create() {
        when(repositoryMock.save(entity)).thenReturn(entity);
        Participacao e = service.createParticipacao(entity);
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }


    @Test
    public void should_listEvents() {
        List<Participacao> list = new ArrayList<>();
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<Participacao> panticipacao = service.listParticipacao();

        verify(repositoryMock, times(1)).findAll();
        assertNotNull("Array não deve ser nulo", panticipacao);
        assertEquals("Array deve ser de tamanho 1", 1, panticipacao.size());
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
        Participacao model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Client deve ser encontrado!", model);
    }
    
    @Test
    public void should_deleteEvento() {
        should_findById();

        // when
        service.deletParticipacao(1);
    }


    @Test
    public void should_updateEvento() {
        should_findById();
        when(repositoryMock.save(entity)).thenReturn(entity);
        Participacao e = service.updateParticipacao(entity, anyInt());
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }


}