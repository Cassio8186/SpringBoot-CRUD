package br.com.cassio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.controller.exceptions.BusinessRuleBrokenException;
import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Pessoa;
import br.com.cassio.crud.model.PessoaFisica;
import br.com.cassio.crud.repository.PessoaFisicaRepository;

@Service
@Transactional
public class PessoaFisicaService {

	private final LogHelper<PessoaFisicaService> log = new LogHelper<>(PessoaFisicaService.class);

	private final PessoaFisicaRepository pessoaFisicaRepository;

	private final ContatoService contatoService;

	private final EnderecoService enderecoService;

	public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository, ContatoService contatoService, EnderecoService enderecoService) {
		this.pessoaFisicaRepository = pessoaFisicaRepository;
		this.contatoService = contatoService;
		this.enderecoService = enderecoService;
	}

	public void delete(Long idPessoaFisica) {
		final String searchParameter = "id";

		Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository
				.findByIdAndExcluirFalse(idPessoaFisica);
		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(PessoaFisica.class, searchParameter, idPessoaFisica);
		}
		PessoaFisica pessoaFisica = optionalPessoaFisica.get();
		pessoaFisica.setExcluir(true);

		pessoaFisica.getEnderecos().forEach(endereco -> {
			enderecoService.delete(endereco.getId());
		});

		pessoaFisica.getContatos().forEach(contato -> {
			contatoService.delete(contato.getId());
		});

		this.pessoaFisicaRepository.save(pessoaFisica);

	}

	public PessoaFisica findByPessoaCpf(String cpf) {
		final String searchParameter = "pessoa.cpf";

		Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findByCpfAndExcluirFalse(cpf);

		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(Pessoa.class, searchParameter, cpf);
		}
		PessoaFisica pessoaFisica = optionalPessoaFisica.get();

		log.info(LogMessage.FOUND, PessoaFisica.class, pessoaFisica);
		return pessoaFisica;
	}

	public PessoaFisica save(PessoaFisica pessoaFisica) {
		Boolean pessoaExists = this.pessoaFisicaRepository.existsByCpfAndExcluirFalse(pessoaFisica.getCpf());

		if (Boolean.TRUE.equals(pessoaExists)) {
			String exceptionMessage = String.format(
					"Não é possível efetuar cadastro pois CPF %s já existe no banco de dados.", pessoaFisica.getCpf());
			throw new BusinessRuleBrokenException(Pessoa.class, "cpf", exceptionMessage);
		}
		log.info(LogMessage.SAVING, pessoaFisica);

		pessoaFisica.setExcluir(false);

		PessoaFisica pessoa = this.pessoaFisicaRepository.save(pessoaFisica);
		return pessoa;
	}

	public PessoaFisica update(PessoaFisica pessoaFisica) {
		Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository
				.findByIdAndExcluirFalse(pessoaFisica.getId());

		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(PessoaFisica.class, "id", pessoaFisica.getId());
		}
		log.info(LogMessage.UPDATING, pessoaFisica);

		PessoaFisica pessoa = this.pessoaFisicaRepository.save(pessoaFisica);
		return pessoa;
	}

	public List<PessoaFisica> findAllPessoasFisicas() {
		log.info(LogMessage.FIND_ALL_BY_REQUEST, "excluir", "false");
		List<PessoaFisica> pessoas = this.pessoaFisicaRepository.findAllByExcluirFalse();
		log.info(LogMessage.FIND_ALL_BY_SUCESS, "excluir", "false");
		return pessoas;
	}
}
