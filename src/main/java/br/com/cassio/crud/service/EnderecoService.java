package br.com.cassio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.Pessoa;
import br.com.cassio.crud.repository.EnderecoRepository;
import br.com.cassio.crud.repository.PessoaRepository;

@Service
@Transactional
public class EnderecoService {
	private final LogHelper<EnderecoService> log = new LogHelper<>(EnderecoService.class);
	private final EnderecoRepository enderecoRepository;
	private final PessoaRepository pessoaRepository;

	public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
		this.enderecoRepository = enderecoRepository;
		this.pessoaRepository = pessoaRepository;

	}

	public void delete(Long idEndereco) {
		Optional<Endereco> optionalEndereco = this.enderecoRepository.findById(idEndereco);
		if (!optionalEndereco.isPresent()) {
			throw new EntityNotFoundException(Endereco.class, "id", idEndereco);
		}
		Endereco endereco = optionalEndereco.get();
		endereco.setExcluir(true);

		this.enderecoRepository.save(endereco);
	}

	public List<Endereco> findAllByPessoaId(Long pessoaId) {
		throwErrorIfPessoaIsDeleted(pessoaId);

		List<Endereco> enderecos = this.enderecoRepository.findAllByPessoaIdAndExcluirFalse(pessoaId);
		log.info(LogMessage.FOUND, Endereco.class, enderecos);
		return enderecos;

	}

	public Endereco save(Endereco endereco) {
		Long idPessoa = endereco.getPessoa().getId();

		throwErrorIfPessoaIsDeleted(idPessoa);
		log.info(LogMessage.SAVING, endereco);

		return this.enderecoRepository.save(endereco);
	}

	private void throwErrorIfPessoaIsDeleted(Long pessoaId) {
		final boolean pessoaDoesNotExists = !this.pessoaRepository.existsByIdAndExcluirFalse(pessoaId);
		if (pessoaDoesNotExists) {
			throw new EntityNotFoundException(Pessoa.class, "Id", pessoaId);
		}
	}

	public Endereco update(Endereco endereco) {

		Optional<Endereco> optionalEndereco = this.enderecoRepository.findById(endereco.getId());
		if (!optionalEndereco.isPresent()) {
			throw new EntityNotFoundException(Endereco.class, "id", endereco.getId());
		}
		log.info(LogMessage.UPDATING, endereco);

		Endereco actualEndereco = optionalEndereco.get();

		final Pessoa pessoa = actualEndereco.getPessoa();
		final Long idPessoa = pessoa.getId();
		throwErrorIfPessoaIsDeleted(idPessoa);

		endereco.setPessoa(pessoa);
		 
		return this.enderecoRepository.save(endereco);
	}

}
