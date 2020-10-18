package br.com.cassio.crud.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("JURIDICA")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = -5324275005687122257L;

	@Column(length = 14, name = "cnpj")
	private String cnpj;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	@Column(name = "contato_responsavel")
	private String contatoResponsavel;

}