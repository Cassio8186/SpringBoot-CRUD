package br.com.cassio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.controller.exceptions.BusinessRuleBrokenException;
import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Pessoa;
import br.com.cassio.crud.model.PessoaJuridica;
import br.com.cassio.crud.repository.PessoaJuridicaRepository;

@Service
@Transactional
public class PessoaJuridicaService {

	private final LogHelper<PessoaJuridicaService> log = new LogHelper<>(PessoaJuridicaService.class);
	private final PessoaJuridicaRepository pessoaJuridicaRepository;

	public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository) {
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
	}

	public void delete(Long idPessoaJuridica) {
		Optional<PessoaJuridica> optionalPessoaJuridica = this.pessoaJuridicaRepository
				.findByIdAndExcluirFalse(idPessoaJuridica);
		if (!optionalPessoaJuridica.isPresent()) {
			throw new EntityNotFoundException(PessoaJuridica.class, "id", idPessoaJuridica);
		}
		PessoaJuridica pessoaJuridica = optionalPessoaJuridica.get();
		pessoaJuridica.setExcluir(true);

		this.pessoaJuridicaRepository.save(pessoaJuridica);
	}

	public List<PessoaJuridica> findAllByPessoaId(Long pessoaId) {
		List<PessoaJuridica> pessoaJuridicas = this.pessoaJuridicaRepository.findAllByExcluirFalse();
		log.info(LogMessage.FOUND, PessoaJuridica.class, pessoaJuridicas);
		return pessoaJuridicas;

	}

	public PessoaJuridica save(PessoaJuridica pessoaJuridica) {

		if (this.pessoaJuridicaRepository.existsByCnpjAndExcluirFalse(pessoaJuridica.getCnpj())) {
			throw new BusinessRuleBrokenException(Pessoa.class, "pessoa.cnpj", pessoaJuridica.getCnpj());
		}
		log.info(LogMessage.SAVING, pessoaJuridica);

		return this.pessoaJuridicaRepository.save(pessoaJuridica);
	}

	public PessoaJuridica update(PessoaJuridica pessoaJuridica) {
		Optional<PessoaJuridica> optionalPessoaJuridica = this.pessoaJuridicaRepository
				.findByIdAndExcluirFalse(pessoaJuridica.getId());

		if (!optionalPessoaJuridica.isPresent()) {
			throw new EntityNotFoundException(PessoaJuridica.class, "id", pessoaJuridica.getId());
		}
		log.info(LogMessage.UPDATING, pessoaJuridica);

		return this.pessoaJuridicaRepository.save(pessoaJuridica);
	}

}
