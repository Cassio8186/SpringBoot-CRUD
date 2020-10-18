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
public class ContatoUpdateDTO {

	@NotNull
	private Long id;

	@ApiModelProperty(example = "FIXO")
	@NotNull
	private TipoContato tipoContato;

	@NotNull
	@ApiModelProperty(example = "91984198723")
	private String telefone;

}
