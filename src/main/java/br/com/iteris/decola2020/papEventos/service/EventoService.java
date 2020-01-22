package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.validators.EventoValidator;
import br.com.iteris.decola2020.papEventos.exception.DataCantBeDeletedException;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.exception.InvalidDateException;
import br.com.iteris.decola2020.papEventos.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento createEvento(Evento model) {

        boolean testeData = EventoValidator.ValidaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
        if(!testeData){
            throw new InvalidDateException("Data Errada Brother");
        }
        return eventoRepository.save(model);
    }

    public List<Evento> listEvento() {
        return eventoRepository.findAll();
    }

	public Evento findById(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElseThrow(() -> new DataNotFoundException("Event Not found"));
    }
    
    public void deletEvento(Integer id)throws DataNotFoundException{
        findById(id);
        
        try {
            eventoRepository.deleteById(id);
        } catch (Exception  e) {
           throw new DataCantBeDeletedException("Can't delete an event that has partcipants alread");
        }
    }

    public Evento updateEvento(Evento model,Integer id) throws DataNotFoundException{
        findById(id);
        boolean testeData = EventoValidator.ValidaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
        if(!testeData){
            throw new InvalidDateException("Data Errada Brother");
        }
        return eventoRepository.save(model);
    }

}