package br.com.cassio.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.crud.model.Contato;

@Repository

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	List<Contato> findAllByExcluirFalse();

	List<Contato> findAllByPessoaIdAndExcluirFalse(long pessoaId);

	Optional<Contato> findByIdAndExcluirFalse(Long id);

}
