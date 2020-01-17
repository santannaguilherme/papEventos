package br.com.iteris.decola2020.papEventos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.decola2020.papEventos.domain.dto.response.StatusEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.mapper.StatusEventoMapper;
import br.com.iteris.decola2020.papEventos.service.StatusEventoService;

@RestController
@RequestMapping("/status-evento")
public class StatusEventoController {

	private final StatusEventoService statusEventoService;
	private final StatusEventoMapper mapper;

	@Autowired
	public StatusEventoController(StatusEventoService statusEventoService, StatusEventoMapper statusEventoMapper) {
		this.statusEventoService = statusEventoService;
		this.mapper = statusEventoMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<StatusEventoResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.toDto(statusEventoService.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<StatusEventoResponse>> list() {
		return ResponseEntity.ok(statusEventoService.listStatusEvento().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

}