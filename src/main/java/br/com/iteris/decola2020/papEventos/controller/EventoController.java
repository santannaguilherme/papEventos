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
import br.com.iteris.decola2020.papEventos.domain.dto.request.EventoUpdateRequest;
import br.com.iteris.decola2020.papEventos.domain.dto.response.EventoResponse;
import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import br.com.iteris.decola2020.papEventos.domain.mapper.EventoMapper;
import br.com.iteris.decola2020.papEventos.service.CategoriaEventoService;
import br.com.iteris.decola2020.papEventos.service.EventoService;
import br.com.iteris.decola2020.papEventos.service.StatusEventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {

	private final EventoService eventoService;
	private final EventoMapper mapper;
	private final StatusEventoService statusEventoService;
	private final CategoriaEventoService categoriaEventoService;

	@Autowired
	public EventoController(EventoService eventoService, EventoMapper eventoMapper,
			StatusEventoService statusEventoService, CategoriaEventoService categoriaEventoService) {
		this.eventoService = eventoService;
		this.mapper = eventoMapper;
		this.statusEventoService = statusEventoService;
		this.categoriaEventoService = categoriaEventoService;
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
		Evento evento = mapper.fromDto(model);
		evento.setCategoria(categoriaEventoService.findById(model.getIdCategoriaEvento()));
		evento.setStatus(statusEventoService.findById(1));
		
		Evento e = eventoService.createEvento(evento);
		return ResponseEntity.ok(mapper.toDto(e));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EventoResponse> updateById(@PathVariable Integer id,
			@Valid @RequestBody EventoUpdateRequest model) {
		Evento evento = mapper.updateFromDto(model,id);
		evento.setCategoria(categoriaEventoService.findById(model.getIdCategoriaEvento()));
		evento.setStatus(statusEventoService.findById(model.getIdEventoStatus()));
		Evento e = eventoService.updateEvento(evento, id);
		return ResponseEntity.ok(mapper.toDto(e));
	}

	@PutMapping(value = "/{id}/cancel")
	public ResponseEntity<EventoResponse> cancel(@PathVariable Integer id) {
		Evento e = eventoService.cancelEvento(id);
		return ResponseEntity.ok(mapper.toDto(e));
	}

}