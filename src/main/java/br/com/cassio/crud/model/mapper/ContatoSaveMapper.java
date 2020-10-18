package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Contato;
import br.com.cassio.crud.model.dto.ContatoSaveDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface ContatoSaveMapper extends EntityMapper<Contato, ContatoSaveDTO> {
	ContatoSaveMapper INSTANCE = Mappers.getMapper(ContatoSaveMapper.class);

	@Mapping(source = "idPessoa", target = "pessoa.id")
	@Mapping(target = "excluir", expression = "java(false)")
	Contato toEntity(ContatoSaveDTO contatoDTO);

	@InheritInverseConfiguration
	ContatoSaveDTO toDto(Contato contato);
}
