package br.com.cassio.crud.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.PessoaJuridica;
import br.com.cassio.crud.model.dto.PessoaJuridicaDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface PessoaJuridicaMapper extends EntityMapper<PessoaJuridica, PessoaJuridicaDTO> {
	PessoaJuridicaMapper INSTANCE = Mappers.getMapper(PessoaJuridicaMapper.class);

}
