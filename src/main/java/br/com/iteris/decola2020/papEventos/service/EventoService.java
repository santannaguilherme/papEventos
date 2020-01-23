package br.com.iteris.decola2020.papEventos.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
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

        boolean testeData = validaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
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
        boolean testeData = validaDatasEvento(model.getDataHoraInicio(), model.getDataHoraFim());
        if (!testeData) {
            throw new InvalidDateException("Data Errada Brother");
        }
        return eventoRepository.save(model);
    }

    public Evento cancelEvento(Integer id) {

        Evento model = findById(id);
        
        if (!validaUpdateDate(model.getDataHoraInicio())) {
            throw new InvalidDateException("Evento não pode ser cancelado no dia!");
        }

        StatusEvento sts = statusEventoService.findById(4);
        model.setStatus(sts);
        return eventoRepository.save(model);
    }


    public boolean validaDatasEvento(Date inicio, Date fim) {

        long dataInicio = inicio.getTime();
        long dataFim = fim.getTime();

        if (dataFim - dataInicio <= 0) {
            return false;
        }

        Calendar cal = Calendar.getInstance();
        int hoje = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(inicio);
        int dIni = cal.get(Calendar.DAY_OF_MONTH);
        int mIni = cal.get(Calendar.MONTH);
        int yIni = cal.get(Calendar.YEAR);
        cal.setTime(fim);
        int dFim = cal.get(Calendar.DAY_OF_MONTH);
        int mFim = cal.get(Calendar.MONTH);
        int yFim = cal.get(Calendar.YEAR);

        if (hoje == dIni) {
            return false;
        }

        if (dIni == dFim && mIni == mFim && yIni == yFim) {
            return true;
        }

        return false;
    }

    public boolean validaUpdateDate(Date e){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String evento = simpleDateFormat.format(e);
        String hoje = simpleDateFormat.format(date);

        return !hoje.equals(evento);
    }
}