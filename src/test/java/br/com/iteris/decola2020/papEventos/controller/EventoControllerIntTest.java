package br.com.iteris.decola2020.papEventos.controller;



import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.repository.EventoRepository;
import br.com.iteris.decola2020.papEventos.utils.IntegrationTestConfig;

/**
 * ClientControllerTestIntTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = IntegrationTestConfig.appProperties)
@ActiveProfiles("test")
public class EventoControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void should_getEmptyList_whenGetEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/evento")) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk()) // faz a validação.
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void should_get404_whenGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/evento/1")) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // faz a validação.
    }

    // @Test
    // public void should_get_whenGetById() throws Exception {

    //     Evento e = Evento.builder().descricao("descricao").nome("nome").dataHoraInicio(new Date())
    //     .dataHoraFim(new Date()).local("local")
    //     .categoria(CategoriaEvento.builder().idCategoriaEvento(1).build())
    //     .status(StatusEvento.builder().idEventoStatus(1).build()).limiteVagas(10).build();

    //     eventoRepository.saveAndFlush(e);

    //     mockMvc.perform(MockMvcRequestBuilders.get("/evento/" + 1)) // Executa
    //             .andDo(MockMvcResultHandlers.print()) // pega resultado
    //             .andExpect(MockMvcResultMatchers.status().isOk()); // faz a validação.
    // }

    @Test
    public void should_return403_whenPostInvalid2() throws Exception {
        // given
        EventoCreateRequest request = EventoCreateRequest.builder()//
                .nome(null).descricao("phone").build();

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.post("/evento") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // faz a validação.
    }
    
    //  @Test public void should_create_whenPostValid() throws Exception { // given
    //  EventoCreateRequest request = buildEventoRequest();
     
    //  // when + then MvcResult result =
    //  mockMvc.perform(MockMvcRequestBuilders.post("/evento") //
    //  .contentType(MediaType.APPLICATION_JSON) //
    //   .content(mapper.writeValueAsString(request))) // Executa
    //   .andDo(MockMvcResultHandlers.print()) // pega resultado
    //   .andExpect(MockMvcResultMatchers.status().isOk()).andReturn(); // faz a
    //   validação.
      
    //   EventoResponse response =
    //   mapper.readValue(result.getResponse().getContentAsString(),
    //   EventoResponse.class);
      
    //   assertNotNull(response.getIdEvento()); }

    private Evento buildEvento() {
        Evento e = Evento.builder().descricao("descricao").nome("nome").dataHoraInicio(new Date())
                .dataHoraFim(new Date()).local("local")
                .categoria(CategoriaEvento.builder().idCategoriaEvento(1).build())
                .status(StatusEvento.builder().idEventoStatus(1).build()).limiteVagas(10).build();
        e.getDataHoraInicio().setTime(e.getDataHoraInicio().getTime() + (24 * 60 * 60 * 1000));
        e.getDataHoraFim().setTime(e.getDataHoraFim().getTime() + (24 * 60 * 60 * 1000) + 30000);
        return e;
    }

    private EventoCreateRequest buildEventoRequest() {
        EventoCreateRequest e = EventoCreateRequest.builder().descricao("descricao").nome("nome")
                .dataHoraInicio(new Date()).dataHoraFim(new Date()).local("local").idCategoriaEvento(1).limiteVagas(10)
                .build();
        e.getDataHoraInicio().setTime(e.getDataHoraInicio().getTime() + (24 * 60 * 60 * 1000));
        e.getDataHoraFim().setTime(e.getDataHoraFim().getTime() + (24 * 60 * 60 * 1000) + 30000);
        return e;
    }
}