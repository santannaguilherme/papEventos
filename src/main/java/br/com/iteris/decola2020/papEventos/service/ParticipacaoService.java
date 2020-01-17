package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.repository.ParticipacaoRepository;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository paticipacaoRepository;

    @Autowired
    public ParticipacaoService(ParticipacaoRepository participacaoRepository) {
        this.paticipacaoRepository = participacaoRepository;
    }

    public Participacao createParticipacao(Participacao model) {
        return paticipacaoRepository.save(model);
    }

    public List<Participacao> listParticipacao() {
        return paticipacaoRepository.findAll();
    }

    public Participacao findById(Integer id) {
        Optional<Participacao> participacao = paticipacaoRepository.findById(id);
        return participacao.orElseThrow(() -> new DataNotFoundException("Part Not found"));
    }

    public void deletParticipacao(Integer id) throws DataNotFoundException {
        findById(id);
        paticipacaoRepository.deleteById(id);
    }

    public Participacao updateParticipacao(Participacao model, Integer id) throws DataNotFoundException {
        
        findById(id);
        return paticipacaoRepository.save(model);
    }

}