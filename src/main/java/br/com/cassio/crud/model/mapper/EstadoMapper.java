package br.com.cassio.crud.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.Estado;
import br.com.cassio.crud.model.dto.EstadoDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstadoMapper extends EntityMapper<Estado, EstadoDTO> {
	EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

}
