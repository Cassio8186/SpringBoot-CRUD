package br.com.cassio.crud.model.dto;

import javax.validation.constraints.NotNull;

import br.com.cassio.crud.model.enumeration.TipoContato;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoSaveDTO {
	@ApiModelProperty(example = "FIXO")
	@NotNull
	private TipoContato tipoContato;

	@ApiModelProperty(example = "91982449301")
	@NotNull
	private String telefone;

	@NotNull
	private Long idPessoa;

}
