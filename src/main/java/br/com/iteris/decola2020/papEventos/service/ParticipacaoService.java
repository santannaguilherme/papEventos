package br.com.iteris.decola2020.papEventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.exception.DataNotFoundException;
import br.com.iteris.decola2020.papEventos.exception.InvalidParticipateException;
import br.com.iteris.decola2020.papEventos.repository.ParticipacaoRepository;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository paticipacaoRepository;

    @Autowired
    public ParticipacaoService(ParticipacaoRepository participacaoRepository) {
        this.paticipacaoRepository = participacaoRepository;
    }

    public Participacao createParticipacao(Participacao model) {
        liberaParticipacao(model.getEvento());
        return paticipacaoRepository.save(model);
    }

    public List<Participacao> listParticipacao() {
        return paticipacaoRepository.findAll();
    }

    public List<Participacao> listParticipacaoByEvento(Evento e) {
        return paticipacaoRepository.findByEvento(e);
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

        Participacao p = findById(id);
        if (!p.getFlagPresente()) {
            throw new InvalidParticipateException("Não pode fazer avaliação se não estava presente");
        }
        p.setNota(model.getNota());
        p.setComentario(model.getComentario());

        return paticipacaoRepository.save(p);
    }

    public void liberaParticipacao(Evento e) {

        if (e.getStatus().getIdEventoStatus() != 1) {
            throw new InvalidParticipateException(
                    "Não pode fazer inscrição em evento que não esta aberto para inscrição");
        }

        int vagas = e.getLimiteVagas();
        int ocupados = paticipacaoRepository.countByEvento(e);

        if (vagas - ocupados <= 0) {
            throw new InvalidParticipateException("Não tem vagas para inscrição");
        }
    }

	public Participacao updatePresParticipacao(Integer id) {
        Participacao p = findById(id);
        if(p.getEvento().getStatus().getIdEventoStatus() == 1 && p.getEvento().getStatus().getIdEventoStatus() == 4){
            throw new InvalidParticipateException("Não pode dar presença sem tem iniciado o evento");
        }
        p.setFlagPresente(true);
        return paticipacaoRepository.save(p);
	}

}