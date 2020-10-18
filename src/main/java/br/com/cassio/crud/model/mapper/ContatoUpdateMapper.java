package br.com.cassio.crud.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Contato;
import br.com.cassio.crud.model.dto.ContatoUpdateDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface ContatoUpdateMapper extends EntityMapper<Contato, ContatoUpdateDTO> {
	ContatoUpdateMapper INSTANCE = Mappers.getMapper(ContatoUpdateMapper.class);

	@Mapping(target = "excluir", expression = "java(false)")
	Contato toEntity(ContatoUpdateDTO dto);
}
