package br.com.iteris.decola2020.papEventos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByCategoria(CategoriaEvento categoria);

    @Query(nativeQuery = true, value = "select IdEvento,IdEventoStatus,IdCategoriaEvento,Nome,DataHoraInicio,DataHoraFim,Local,Descricao,LimiteVagas from Evento where IdEventoStatus = 1")
    List<Evento> listAberto();

    @Query(nativeQuery = true, value = "select * from Evento where convert(varchar,DataHoraInicio, 1) =  convert(varchar,?1, 1)")
    List<Evento> listByDate(Date data);
}