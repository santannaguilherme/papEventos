package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.domain.validators.EventoValidator;
import br.com.iteris.decola2020.papEventos.exception.DataCantBeDeletedException;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.exception.InvalidDateException;
import br.com.iteris.decola2020.papEventos.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final StatusEventoService statusEventoService;

    @Autowired
    public EventoService(EventoRepository eventoRepository, StatusEventoService statusEventoService) {
        this.eventoRepository = eventoRepository;
        this.statusEventoService = statusEventoService;
    }

    public Evento createEvento(Evento model) {

        boolean testeData = EventoValidator.validaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
        if (!testeData) {
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

    public void deletEvento(Integer id) throws DataNotFoundException {
        findById(id);

        try {
            eventoRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataCantBeDeletedException("Can't delete an event that has partcipants alread");
        }
    }

    public Evento updateEvento(Evento model, Integer id) throws DataNotFoundException {
        findById(id);
        boolean testeData = EventoValidator.validaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
        if (!testeData) {
            throw new InvalidDateException("Data Errada Brother");
        }
        return eventoRepository.save(model);
    }

    public Evento cancelEvento(Integer id) {

        Evento model = findById(id);
        
        if (!EventoValidator.validaUpdateDate(model.getDataHoraInicio())) {
            throw new InvalidDateException("Evento não pode ser cancelado no dia!");
        }

        StatusEvento sts = statusEventoService.findById(4);
        model.setStatus(sts);
        return eventoRepository.save(model);
    }

}