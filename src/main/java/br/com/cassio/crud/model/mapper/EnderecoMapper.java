package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Endereco;
import br.com.cassio.crud.model.dto.EnderecoDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CidadeMapper.class })
public interface EnderecoMapper extends EntityMapper<Endereco, EnderecoDTO> {
	EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

	@Mapping(source = "idCidade", target = "cidade.id")
	@Mapping(source = "cidade", target = "cidade.nome")
	@Mapping(source = "idPessoa", target = "pessoa.id")
	@Mapping(source = "idEstado", target = "cidade.estado.id")
	@Mapping(source = "estado", target = "cidade.estado.nome")
	@Mapping(target = "excluir", expression = "java(false)")
	Endereco toEntity(EnderecoDTO enderecoDTO);

	@InheritInverseConfiguration
	EnderecoDTO toDto(Endereco endereco);

}
