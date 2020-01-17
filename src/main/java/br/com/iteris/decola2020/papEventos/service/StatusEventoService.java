package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.repository.StatusEventoRepository;

@Service
public class StatusEventoService {

    private final StatusEventoRepository statusEventoRepository;

    @Autowired
    public StatusEventoService(StatusEventoRepository statusEventoRepository) {
        this.statusEventoRepository = statusEventoRepository;
    }

    public StatusEvento createEvento(StatusEvento model) {
        return statusEventoRepository.save(model);
    }

    public List<StatusEvento> listStatusEvento() {
        return statusEventoRepository.findAll();
    }

	public StatusEvento findById(Integer id) {
        Optional<StatusEvento> evento = statusEventoRepository.findById(id);
        return evento.orElseThrow(() -> new DataNotFoundException("Event Not found"));
    }
    
    public void deletEvento(Integer id)throws DataNotFoundException{
        findById(id);
        statusEventoRepository.deleteById(id);
    }

    public StatusEvento updateEvento(StatusEvento model,Integer id) throws DataNotFoundException{
        StatusEvento e = findById(id);
        /*c.setName(model.getName());
        c.setPhone(model.getPhone());
        
        */
        return statusEventoRepository.save(e);
    }

}