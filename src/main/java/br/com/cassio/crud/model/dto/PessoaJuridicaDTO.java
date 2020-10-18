package br.com.cassio.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridicaDTO {

	private Long id;

	private String cnpj;

	private String email;

	private String razaoSocial;

	private String nomeFantasia;

	private String nomeResponsavel;

	private String contatoResponsavel;

}
