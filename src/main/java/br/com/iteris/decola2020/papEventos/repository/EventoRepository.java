package br.com.iteris.decola2020.papEventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}