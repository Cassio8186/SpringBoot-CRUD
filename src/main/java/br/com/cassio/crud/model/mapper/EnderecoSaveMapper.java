package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.dto.EnderecoSaveDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface EnderecoSaveMapper extends EntityMapper<Endereco, EnderecoSaveDTO> {
	EnderecoSaveMapper INSTANCE = Mappers.getMapper(EnderecoSaveMapper.class);

	@Mapping(source = "idPessoa", target = "pessoa.id")
	@Mapping(source = "idCidade", target = "cidade.id")
	@Mapping(target = "excluir", expression = "java(false)")
	Endereco toEntity(EnderecoSaveDTO dto);

	@InheritInverseConfiguration
	EnderecoSaveDTO toDto(Endereco entity);
}
