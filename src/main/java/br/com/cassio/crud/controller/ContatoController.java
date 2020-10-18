package br.com.cassio.crud.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Contato;
import br.com.cassio.crud.model.dto.ContatoDTO;
import br.com.cassio.crud.model.dto.ContatoSaveDTO;
import br.com.cassio.crud.model.dto.ContatoUpdateDTO;
import br.com.cassio.crud.model.mapper.ContatoMapper;
import br.com.cassio.crud.model.mapper.ContatoSaveMapper;
import br.com.cassio.crud.model.mapper.ContatoUpdateMapper;
import br.com.cassio.crud.service.ContatoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/v1/contatos")
public class ContatoController {
	private final LogHelper<ContatoController> log = new LogHelper<>(ContatoController.class);
	private final ContatoService contatoService;

	public ContatoController(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	@DeleteMapping(
			value = "/{id-contato}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Deleta um contato de uma pessoa.")
	public ResponseEntity<Void> deleteContato(@PathVariable("id-contato") Long idContato) {
		final String searchParameter = "contato.id";
		log.info(LogMessage.DELETE_SOFT_REQUEST, searchParameter, idContato);
		try {

			this.contatoService.delete(idContato);
			;
			log.info(LogMessage.DELETE_SOFT_SUCESS, searchParameter, idContato);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error(LogMessage.DELETE_SOFT_FAIL, searchParameter, idContato);
			throw e;
		}
	}

	@GetMapping(value = "/{id-pessoa}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna uma lista dos contatos de uma pessoa")
	public ResponseEntity<List<ContatoDTO>> findContatoByPessoaId(@PathVariable("id-pessoa") Long idPessoa) {
		final String searchParameter = "contato.pessoa.id";
		log.info(LogMessage.FIND_BY_REQUEST, searchParameter, idPessoa);

		List<Contato> contatos = this.contatoService.findAllByPessoaId(idPessoa);
		List<ContatoDTO> contatosDTO = ContatoMapper.INSTANCE.toDto(contatos);
		log.info(LogMessage.FIND_ALL_BY_SUCESS, searchParameter, idPessoa);
		return ResponseEntity.ok(contatosDTO);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Salva um contato de uma pessoa")
	public ResponseEntity<ContatoDTO> saveContato(@RequestBody @Validated ContatoSaveDTO contatoSaveDTO) {
		log.info(LogMessage.SAVE_REQUEST, contatoSaveDTO);
		try {
			Contato contato = ContatoSaveMapper.INSTANCE.toEntity(contatoSaveDTO);

			contato.setExcluir(false);
			contato = this.contatoService.save(contato);
			ContatoDTO contatoDTO = ContatoMapper.INSTANCE.toDto(contato);

			return ResponseEntity.ok(contatoDTO);
		} catch (Exception e) {
			log.error(LogMessage.SAVE_FAIL, contatoSaveDTO);
			throw e;
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Atualiza um contato pelo id")
	public ResponseEntity<ContatoDTO> updateContato(@RequestBody @Validated ContatoUpdateDTO contatoUpdateDTO) {
		log.info(LogMessage.SAVE_REQUEST, contatoUpdateDTO);
		try {
			Contato contato = ContatoUpdateMapper.INSTANCE.toEntity(contatoUpdateDTO);
			contato = this.contatoService.update(contato);
			ContatoDTO contatoDTO = ContatoMapper.INSTANCE.toDto(contato);

			return ResponseEntity.ok(contatoDTO);
		} catch (Exception e) {
			log.error(LogMessage.SAVE_FAIL, contatoUpdateDTO);
			throw e;
		}
	}
}
