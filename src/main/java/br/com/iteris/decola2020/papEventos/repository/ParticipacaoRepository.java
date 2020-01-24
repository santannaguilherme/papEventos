package br.com.iteris.decola2020.papEventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {

    Integer countByEvento(Evento e);
    List<Participacao> findByEvento(Evento e);

}