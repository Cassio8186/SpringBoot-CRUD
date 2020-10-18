package br.com.cassio.crud.controller.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cassio.crud.model.dto.ErrorDTO;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusinessRuleBrokenException.class)
	protected ResponseEntity<Object> businessRuleBrokenAdvice(BusinessRuleBrokenException ex, WebRequest request) {
		String error = ex.getMessage();

		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	protected ResponseEntity<Object> EntityAlreadyExistsAdvice(EntityAlreadyExistsException ex, WebRequest request) {
		String error = ex.getMessage();

		ErrorDTO errorDTO = ErrorDTO.builder().exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> entityNotFound(EntityNotFoundException ex, WebRequest request) {
		String error = ex.getMessage();
		ErrorDTO errorDTO = ErrorDTO
				.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(errors)
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		String error = ex.getMessage();
		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(builder.toString()))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(errors)
				.build();

		return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getStatusCode(), request);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(error))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> pSQLExceptionAdvice(DataIntegrityViolationException ex, WebRequest request) {

		ErrorDTO errorDTO = ErrorDTO.builder()
				.exception(ex.getClass().getSimpleName())
				.message(ex.getLocalizedMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.statusCode(HttpStatus.BAD_REQUEST)
				.errors(Arrays.asList(ex.getMessage()))
				.build();

		return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatusCode());
	}
}
