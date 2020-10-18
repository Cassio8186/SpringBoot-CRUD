package br.com.cassio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.crud.controller.exceptions.EntityNotFoundException;
import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;
import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.Estado;
import br.com.cassio.crud.repository.EstadoRepository;

@Service
@Transactional
public class EstadoService {

	private final LogHelper<EstadoService> log = new LogHelper<>(EstadoService.class);

	private final EstadoRepository estadoRepository;

	public EstadoService(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	public List<Estado> findAll() {
		return this.estadoRepository.findAll();
	}

	public Estado findBySigla(String sigla) {
		Optional<Estado> optionalEstado = this.estadoRepository.findBySigla(sigla);
		if (!optionalEstado.isPresent()) {
			throw new EntityNotFoundException(Estado.class, "estado.sigla", sigla);
		}
		Estado estado = optionalEstado.get();
		log.info(LogMessage.FOUND, Endereco.class, estado);
		return estado;

	}

}
