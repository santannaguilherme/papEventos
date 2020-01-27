
package br.com.iteris.decola2020.papEventos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.exception.InvalidParticipateException;
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

   

    private final String nome = "Nome";
    private final Date dataHoraInicio = new Date();
    private final Date dataHoraFim = new Date();
    private final String local = "Local";
    private final String descricao = "Qualquer coisa";
    private final Integer limiteVagas = 20;

    Evento evento = Evento.builder().descricao(descricao).nome(nome).dataHoraInicio(dataHoraInicio)
            .dataHoraFim(dataHoraFim).local(local).categoria(new CategoriaEvento()).status(new StatusEvento())
            .limiteVagas(limiteVagas).build();

            Participacao entity = Participacao.builder().loginParticipante(loginParticipante)
            .flagPresente(flagPresente)
            .nota(nota)
            .comentario(comentario)
            .evento(evento)
            .build();

    @Test
    public void should_create() {
        
        entity.getEvento().getStatus().setIdEventoStatus(1);
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
    public void should_deleteParticipacao() {
        should_findById();

        // when
        service.deletParticipacao(1);
    }


    @Test
    public void should_updateEvento() {
        should_findById();
        entity.setFlagPresente(true);
        when(repositoryMock.save(entity)).thenReturn(entity);

        Participacao e = service.updateParticipacao(entity, anyInt());
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }

    @Test
    public void should_findByPaticipantes() {
        List<Participacao> list = new ArrayList<>();
        list.add(entity);
        // given
        when(repositoryMock.findByEvento(new Evento())).thenReturn(list);

        // when
        List<Participacao> model = service.listParticipacaoByEvento(new Evento());
        // then
        verify(repositoryMock, times(1)).findByEvento(new Evento());
        assertNotNull("Array não deve ser nulo", model);
        assertEquals("Array deve ser de tamanho 1", 1, model.size());
    }

    @Test
    public void should_liberarParticipacao(){
        Evento e = new Evento();
        StatusEvento sts = new StatusEvento();
        sts.setIdEventoStatus(2);
        e.setStatus(sts);
        expected.expect(InvalidParticipateException.class);

        service.liberaParticipacao(e);
    }

    @Test
    public void should_liberarnotParticipacao(){
        Evento e = new Evento();
        StatusEvento sts = new StatusEvento();
        sts.setIdEventoStatus(1);
        e.setStatus(sts);
        e.setLimiteVagas(1);
        when(repositoryMock.countByEvento(e)).thenReturn(1);
        expected.expect(InvalidParticipateException.class);

        service.liberaParticipacao(e);
    }

    @Test
    public void should_liberarnotParticipacaos(){
        Evento e = new Evento();
        StatusEvento sts = new StatusEvento();
        sts.setIdEventoStatus(1);
        e.setStatus(sts);
        e.setLimiteVagas(4);
        when(repositoryMock.countByEvento(e)).thenReturn(1);

        service.liberaParticipacao(e);
        
    }

    
    @Test
    public void should_updateFlagEvento() {
        should_findById();
        entity.getEvento().getStatus().setIdEventoStatus(2);;
        when(repositoryMock.save(entity)).thenReturn(entity);

        Participacao e = service.updatePresParticipacao(anyInt());
        assertNotNull("Model não deve ser nulo", e);
        assertEquals(entity, e);
    }

    @Test
    public void should_updateNotFlagEvento() {
        should_findById();
        entity.getEvento().getStatus().setIdEventoStatus(1);
       

        expected.expect(InvalidParticipateException.class);
        service.updatePresParticipacao(anyInt());
        
    }
    @Test
    public void should_updateNotNotFlagEvento() {
        should_findById();
        entity.getEvento().getStatus().setIdEventoStatus(4);

        expected.expect(InvalidParticipateException.class);
        service.updatePresParticipacao(anyInt());
        
    }

    @Test
    public void should_updateNotNotUpdateEvento() {
        should_findById();
        entity.setFlagPresente(false);

        expected.expect(InvalidParticipateException.class);
        service.updateParticipacao(entity,anyInt());
        
    }

}