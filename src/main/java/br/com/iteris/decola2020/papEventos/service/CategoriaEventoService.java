package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.repository.CategoriaEventoRepository;

@Service
public class CategoriaEventoService {

    private final CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    public CategoriaEventoService(CategoriaEventoRepository categoriaEventoRepository) {
        this.categoriaEventoRepository = categoriaEventoRepository;
    }

    public CategoriaEvento createEvento(CategoriaEvento model) {
        return categoriaEventoRepository.save(model);
    }

    public List<CategoriaEvento> listEvento() {
        return categoriaEventoRepository.findAll();
    }

	public CategoriaEvento findById(Integer id) {
        Optional<CategoriaEvento> evento = categoriaEventoRepository.findById(id);
        return evento.orElseThrow(() -> new DataNotFoundException("Event Not found"));
    }
    
    public void deletEvento(Integer id)throws DataNotFoundException{
        findById(id);
        categoriaEventoRepository.deleteById(id);
    }

    public CategoriaEvento updateEvento(CategoriaEvento model,Integer id) throws DataNotFoundException{
        CategoriaEvento e = findById(id);
        /*c.setName(model.getName());
        c.setPhone(model.getPhone());
        
        */
        return categoriaEventoRepository.save(e);
    }

}