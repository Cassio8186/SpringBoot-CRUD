package br.com.cassio.crud.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

	private Long id;

	@ApiModelProperty(example = "44182901")
	private String cep;

	@ApiModelProperty(example = "Rua cereijão na maionese")
	private String logradouro;

	@ApiModelProperty(example = "Ronicleide")
	private String bairro;

	private Long idPessoa;

	private Long idCidade;

	private Long idEstado;

	@ApiModelProperty(example = "Rio de januário")
	private String cidade;

	@ApiModelProperty(example = "Rio de januário")
	private String estado;

}
