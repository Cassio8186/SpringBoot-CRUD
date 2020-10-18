package br.com.cassio.crud.model.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoUpdateDTO {

	@NotNull
	private Long id;

	@NotNull
	@ApiModelProperty(example = "44182901")
	@Length(min = 8, max = 8)
	private String cep;

	@NotNull
	@ApiModelProperty(example = "Rua cereijão na maionese")
	private String logradouro;

	@NotNull
	@ApiModelProperty(example = "Ronicleide")
	private String bairro;

	@NotNull
	private Long idCidade;

}
