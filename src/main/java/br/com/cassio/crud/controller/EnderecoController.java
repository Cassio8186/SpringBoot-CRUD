package br.com.cassio.crud.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Cidade;
import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.dto.EnderecoDTO;
import br.com.cassio.crud.model.dto.EnderecoSaveDTO;
import br.com.cassio.crud.model.dto.EnderecoUpdateDTO;
import br.com.cassio.crud.model.mapper.EnderecoMapper;
import br.com.cassio.crud.model.mapper.EnderecoSaveMapper;
import br.com.cassio.crud.model.mapper.EnderecoUpdateMapper;
import br.com.cassio.crud.repository.CidadeRepository;
import br.com.cassio.crud.service.EnderecoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/v1/enderecos")
public class EnderecoController {
	private final LogHelper<EnderecoController> log = new LogHelper<>(EnderecoController.class);
	private final EnderecoService enderecoService;
	private final CidadeRepository cidadeRepository;

	public EnderecoController(EnderecoService enderecoService, CidadeRepository cidadeRepository) {
		this.enderecoService = enderecoService;
		this.cidadeRepository = cidadeRepository;
	}

	@DeleteMapping(value = "/{id-endereco}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Deleta um endereço de uma pessoa.")
	public ResponseEntity<Void> deleteEndereco(@PathVariable("id-endereco") Long idEndereco) {
		final String searchParameter = "endereco.id";
		log.info(LogMessage.DELETE_SOFT_REQUEST, searchParameter, idEndereco);
		try {

			this.enderecoService.delete(idEndereco);

			log.info(LogMessage.DELETE_SOFT_SUCESS, searchParameter, idEndereco);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error(LogMessage.DELETE_SOFT_FAIL, "endereco.id", idEndereco);
			throw e;
		}
	}

	@GetMapping(value = "/{id-pessoa}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna uma lista dos endereços de uma pessoa.")
	public ResponseEntity<List<EnderecoDTO>> findEnderecoByPessoaId(@PathVariable("id-pessoa") Long idPessoa) {
		log.info(LogMessage.FIND_BY_REQUEST, "endereco.pessoa.id", idPessoa);

		List<Endereco> enderecos = this.enderecoService.findAllByPessoaId(idPessoa);
		List<EnderecoDTO> enderecosDTO = EnderecoMapper.INSTANCE.toDto(enderecos);
		log.info(LogMessage.FIND_ALL_BY_SUCESS, "endereco.pessoa.id", idPessoa);
		return ResponseEntity.ok(enderecosDTO);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Salva um endereço de uma pessoa.")
	public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody @Validated EnderecoSaveDTO enderecoSaveDTO) {
		log.info(LogMessage.SAVE_REQUEST, enderecoSaveDTO);
		try {
			Optional<Cidade> optionalCidade = cidadeRepository.findById(enderecoSaveDTO.getIdCidade());
			if (!optionalCidade.isPresent()) {
				throw new EntityNotFoundException(Cidade.class, "id", enderecoSaveDTO.getIdCidade());
			}

			Endereco endereco = EnderecoSaveMapper.INSTANCE.toEntity(enderecoSaveDTO);

			endereco.setExcluir(false);

			endereco = this.enderecoService.save(endereco);

			endereco.setCidade(optionalCidade.get());

			EnderecoDTO enderecoDTO = EnderecoMapper.INSTANCE.toDto(endereco);

			return ResponseEntity.ok(enderecoDTO);
		} catch (Exception e) {
			log.error(LogMessage.SAVE_FAIL, enderecoSaveDTO);
			throw e;
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Atualiza o endereço de uma pessoa pelo id")
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody @Validated EnderecoUpdateDTO enderecoUpdateDTO) {
		log.info(LogMessage.SAVE_REQUEST, enderecoUpdateDTO);
		try {
			Endereco endereco = EnderecoUpdateMapper.INSTANCE.toEntity(enderecoUpdateDTO);
			endereco = this.enderecoService.update(endereco);
			EnderecoDTO enderecoDTO = EnderecoMapper.INSTANCE.toDto(endereco);

			return ResponseEntity.ok(enderecoDTO);
		} catch (Exception e) {
			log.error(LogMessage.SAVE_FAIL, enderecoUpdateDTO);
			throw e;
		}
	}
}
