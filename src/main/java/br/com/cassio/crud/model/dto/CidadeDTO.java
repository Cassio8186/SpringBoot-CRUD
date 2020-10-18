package br.com.cassio.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDTO {

	private Long id;

	private String nome;

	private String codigo;

	private String SiglaEstado;

}
