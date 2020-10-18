package br.com.cassio.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.model.Cidade;
import br.com.cassio.crud.repository.CidadeRepository;

@Service
@Transactional
public class CidadeService {

	private final CidadeRepository cidadeRepository;

	public CidadeService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	public List<Cidade> findAll() {
		return this.cidadeRepository.findAll();
	}

	public List<Cidade> findAllByEstadoSigla(String sigla) {
		return this.cidadeRepository.findAllByEstadoSigla(sigla);
	}

}
