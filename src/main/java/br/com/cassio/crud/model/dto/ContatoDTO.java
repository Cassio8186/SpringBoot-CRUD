package br.com.cassio.crud.model.dto;

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
public class ContatoDTO {

	private Long id;

	@ApiModelProperty(example = "CELULAR")
	private TipoContato tipoContato;

	@ApiModelProperty(example = "91983945900")
	private String telefone;

	private Long idPessoa;

}
