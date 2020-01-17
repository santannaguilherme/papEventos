package br.com.iteris.decola2020.papEventos.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.decola2020.papEventos.domain.dto.request.ParticipacaoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.ParticipacaoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Participacao;
import br.com.iteris.decola2020.papEventos.domain.mapper.ParticipacaoMapper;
import br.com.iteris.decola2020.papEventos.service.EventoService;
import br.com.iteris.decola2020.papEventos.service.ParticipacaoService;

@RestController
@RequestMapping("/participacao")
public class ParticipacaoController {

	private final ParticipacaoService participacaoService;
	private final EventoService eventoService;
	private final ParticipacaoMapper mapper;

	@Autowired
	public ParticipacaoController(ParticipacaoService participacaoService, ParticipacaoMapper participacaoMapper,
			EventoService eventoService) {
		this.participacaoService = participacaoService;
		this.mapper = participacaoMapper;
		this.eventoService = eventoService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipacaoResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.toDto(participacaoService.findById(id)));
	}

	@DeleteMapping(value = "/{id}")
	public void deletById(@PathVariable Integer id) {
		participacaoService.deletParticipacao(id);
	}

	@GetMapping
	public ResponseEntity<List<ParticipacaoResponse>> list() {
		return ResponseEntity.ok(participacaoService.listParticipacao().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ParticipacaoResponse> post(@Valid @RequestBody ParticipacaoCreateRequest model) {
		Participacao participacao = mapper.fromDto(model);
		participacao.setEvento(eventoService.findById(model.getIdEvento()));

		Participacao p = participacaoService.createParticipacao(participacao);

		return ResponseEntity.ok(mapper.toDto(p));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ParticipacaoResponse> updateById(@PathVariable Integer id,
			@Valid @RequestBody ParticipacaoCreateRequest model) {

		Participacao participacao = mapper.updateFromDto(model, id);
		participacao.setEvento(eventoService.findById(model.getIdEvento()));

		Participacao p = participacaoService.updateParticipacao(participacao, id);
		return ResponseEntity.ok(mapper.toDto(p));
	}

}