package br.com.iteris.decola2020.papEventos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.decola2020.papEventos.domain.dto.response.CategoriaEventoResponse;
import br.com.iteris.decola2020.papEventos.domain.mapper.CategoriaEventoMapper;
import br.com.iteris.decola2020.papEventos.service.CategoriaEventoService;

@RestController
@RequestMapping("/categoria-evento")
public class CategoriaEventoController {

	private final CategoriaEventoService categoriaEventoService;
	private final CategoriaEventoMapper mapper;

	@Autowired
	public CategoriaEventoController(CategoriaEventoService categoriaEventoService, CategoriaEventoMapper categoriaEventoMapper) {
		this.categoriaEventoService = categoriaEventoService;
		this.mapper = categoriaEventoMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaEventoResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.toDto(categoriaEventoService.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<CategoriaEventoResponse>> list() {
		return ResponseEntity.ok(categoriaEventoService.listCategoriaEvento().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

}