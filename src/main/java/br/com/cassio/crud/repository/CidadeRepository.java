package br.com.cassio.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.crud.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	public List<Cidade> findAllByEstadoSigla(String sigla);

	Optional<Cidade> findById(Long id);
}
