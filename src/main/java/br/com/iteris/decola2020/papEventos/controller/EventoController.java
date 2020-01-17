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

import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoCreateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.EventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.mapper.EventoMapper;
import br.com.iteris.decola2020.papEventos.service.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {

	private final EventoService eventoService;
	private final EventoMapper mapper;

	@Autowired
	public EventoController(EventoService eventoService, EventoMapper eventoMapper) {
		this.eventoService = eventoService;
		this.mapper = eventoMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.toDto(eventoService.findById(id)));
	}

	@DeleteMapping(value = "/{id}")
	public void deletById(@PathVariable Integer id) {
		eventoService.deletEvento(id);
	}

	@GetMapping
	public ResponseEntity<List<EventoResponse>> list() {
		return ResponseEntity.ok(eventoService.listEvento().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<EventoResponse> post(@Valid @RequestBody EventoCreateRequest model) {

		Evento evento = eventoService.createEvento(mapper.fromDto(model));

		return ResponseEntity.ok(mapper.toDto(evento));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EventoResponse> updateById(@PathVariable Integer id,
			@Valid @RequestBody EventoCreateRequest model) {
		Evento evento = eventoService.updateEvento(mapper.fromDto(model), id);
		return ResponseEntity.ok(mapper.toDto(evento));
	}

}