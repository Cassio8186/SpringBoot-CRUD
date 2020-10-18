package br.com.cassio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Contato;
import br.com.cassio.crud.model.Pessoa;
import br.com.cassio.crud.repository.ContatoRepository;
import br.com.cassio.crud.repository.PessoaRepository;

@Service
@Transactional
public class ContatoService {
	private final LogHelper<ContatoService> log = new LogHelper<>(ContatoService.class);
	private final ContatoRepository contatoRepository;
	private final PessoaRepository pessoaRepository;

	public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
		this.contatoRepository = contatoRepository;
		this.pessoaRepository = pessoaRepository;

	}

	public void delete(Long idContato) {
		Optional<Contato> optionalContato = this.contatoRepository.findById(idContato);
		if (!optionalContato.isPresent()) {
			throw new EntityNotFoundException(Contato.class, "id", idContato);
		}
		Contato contato = optionalContato.get();
		contato.setExcluir(true);

		this.contatoRepository.save(contato);
	}

	public List<Contato> findAllByPessoaId(Long pessoaId) {
		throwErrorIfPessoaIsDeleted(pessoaId);

		List<Contato> contatos = this.contatoRepository.findAllByPessoaIdAndExcluirFalse(pessoaId);
		log.info(LogMessage.FOUND, Contato.class, contatos);
		return contatos;

	}

	public Contato save(Contato contato) {
		throwErrorIfPessoaIsDeleted(contato.getPessoa().getId());

		log.info(LogMessage.SAVING, contato);

		return this.contatoRepository.save(contato);
	}

	private void throwErrorIfPessoaIsDeleted(Long pessoaId) {
		final boolean pessoaDoesNotExist = !this.pessoaRepository.existsByIdAndExcluirFalse(pessoaId);
		if (pessoaDoesNotExist) {
			throw new EntityNotFoundException(Pessoa.class, "id", pessoaId);
		}
	}

	public Contato update(Contato contato) {

		Optional<Contato> optionalContato = this.contatoRepository.findById(contato.getId());
		if (!optionalContato.isPresent()) {
			throw new EntityNotFoundException(Contato.class, "id", contato.getId());
		}
		Contato savedContato = optionalContato.get();

		final Long idPessoa = savedContato.getPessoa().getId();
		throwErrorIfPessoaIsDeleted(idPessoa);

		savedContato.setTelefone(contato.getTelefone());
		savedContato.setTipoContato(contato.getTipoContato());

		log.info(LogMessage.UPDATING, contato);

		return this.contatoRepository.save(savedContato);
	}
}
