package br.com.cassio.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.crud.model.PessoaJuridica;

@Repository

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
	Boolean existsByCnpjAndExcluirFalse(String cnpj);

	List<PessoaJuridica> findAllByExcluirFalse();

	Optional<PessoaJuridica> findByIdAndExcluirFalse(Long id);
}
