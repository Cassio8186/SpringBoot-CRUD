package br.com.cassio.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassio.crud.model.Cidade;
import br.com.cassio.crud.model.dto.CidadeDTO;
import br.com.cassio.crud.model.mapper.CidadeMapper;
import br.com.cassio.crud.service.CidadeService;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
public class CidadeController {

	private final CidadeService cidadeService;

	public CidadeController(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	@GetMapping(value = "api/v1/cidades/{sigla-estado}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna uma lista de cidades de um estado")
	public List<CidadeDTO> findAllCidadesByEstadoSigla(@PathVariable("sigla-estado") String sigla) {
		List<Cidade> cidades = this.cidadeService.findAllByEstadoSigla(sigla);
		return CidadeMapper.INSTANCE.toDto(cidades);
	}

}
