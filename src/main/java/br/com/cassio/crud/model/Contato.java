package br.com.cassio.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.cassio.crud.model.enumeration.TipoContato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato implements Serializable {

	private static final long serialVersionUID = 6850353708099955412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_contato", nullable = false)
	private TipoContato tipoContato;

	@Column(name = "telefone", nullable = false)
	private String telefone;

	@JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_contato_id"), nullable = false)
	@ManyToOne
	private Pessoa pessoa;

	private Boolean excluir;

}
