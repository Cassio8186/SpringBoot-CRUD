package br.com.cassio.crud.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 8911978209490691030L;

	@NotNull
	private String exception;

	@NotNull
	private String message;

	@NotNull
	private int code;

	@NotNull
	private HttpStatus statusCode;

	@NotNull
	private List<String> errors;
}
