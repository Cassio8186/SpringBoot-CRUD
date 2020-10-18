package br.com.cassio.crud.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Cidade;
import br.com.cassio.crud.model.dto.CidadeDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { EstadoMapper.class })
public interface CidadeMapper extends EntityMapper<Cidade, CidadeDTO> {
	CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

	@Mapping(source = "estado.sigla", target = "siglaEstado")
	CidadeDTO toDto(Cidade cidade);

	@InheritInverseConfiguration
	Cidade toEntity(CidadeDTO cidadeDTO);
}
