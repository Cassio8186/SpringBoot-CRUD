package br.com.cassio.crud.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassio.crud.model.Estado;
import br.com.cassio.crud.model.dto.EstadoDTO;
import br.com.cassio.crud.model.mapper.EstadoMapper;
import br.com.cassio.crud.service.EstadoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/estados")
public class EstadoController {

	private final EstadoService estadoService;

	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna uma lista com todos os estados")
	public List<EstadoDTO> findAllEstados() {
		List<Estado> estados = this.estadoService.findAll();
		return EstadoMapper.INSTANCE.toDto(estados);
	}

	@GetMapping(value = "/{sigla-estado}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna um estado pela sigla")
	public EstadoDTO findEstadoBySigla(@PathVariable("sigla-estado") String sigla) {
		Estado estado = this.estadoService.findBySigla(sigla);
		return EstadoMapper.INSTANCE.toDto(estado);
	}
}
