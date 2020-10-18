package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Contato;
import br.com.cassio.crud.model.dto.ContatoDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface ContatoMapper extends EntityMapper<Contato, ContatoDTO> {
	ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

	@Mapping(source = "idPessoa", target = "pessoa.id")
	@Mapping(target = "excluir", expression = "java(false)")
	Contato toEntity(ContatoDTO contatoDTO);

	@InheritInverseConfiguration
	ContatoDTO toDto(Contato contato);
}
