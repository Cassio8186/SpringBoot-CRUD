package br.com.cassio.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.crud.model.Pessoa;
import br.com.cassio.crud.model.PessoaFisica;

@Repository

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	Optional<PessoaFisica> findByIdAndExcluirFalse(Long id);

	Boolean existsByIdAndExcluirFalse(Long id);
}
