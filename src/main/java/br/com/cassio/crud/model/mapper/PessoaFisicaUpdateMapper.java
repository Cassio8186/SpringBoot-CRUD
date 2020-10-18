package br.com.cassio.crud.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.crud.model.PessoaFisica;
import br.com.cassio.crud.model.dto.PessoaFisicaUpdateDTO;
import br.com.cassio.crud.model.enumeration.TipoPessoa;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {}, imports = TipoPessoa.class)
public interface PessoaFisicaUpdateMapper extends EntityMapper<PessoaFisica, PessoaFisicaUpdateDTO> {
	PessoaFisicaUpdateMapper INSTANCE = Mappers.getMapper(PessoaFisicaUpdateMapper.class);

	@Mapping(target = "tipoPessoa", expression = "java(TipoPessoa.FISICA)")
	@Mapping(target = "excluir", expression = "java(false)")
	PessoaFisica toEntity(PessoaFisicaUpdateDTO dto);

}
