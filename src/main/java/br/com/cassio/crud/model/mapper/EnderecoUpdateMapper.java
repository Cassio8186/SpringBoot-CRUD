package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.dto.EnderecoUpdateDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface EnderecoUpdateMapper extends EntityMapper<Endereco, EnderecoUpdateDTO> {
	EnderecoUpdateMapper INSTANCE = Mappers.getMapper(EnderecoUpdateMapper.class);

	@Mapping(source = "idCidade", target = "cidade.id")
	@Mapping(target = "excluir", expression = "java(false)")
	Endereco toEntity(EnderecoUpdateDTO dto);

	@InheritInverseConfiguration
	EnderecoUpdateDTO toDto(Endereco entity);

}
