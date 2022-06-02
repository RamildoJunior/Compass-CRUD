package br.com.projeto.product.config.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exceptionMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		List<String> messages = new ArrayList<String>();
		fieldErrors.forEach(e -> {
			messages.add("o campo " + e.getField() + " " + e.getDefaultMessage());
		});
		return new ResponseEntity<>(new ExceptionResponses(HttpStatus.BAD_REQUEST.value(), messages),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> exceptionMessageNoReadable(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(
				new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Required request body is missing!"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> exceptionTypeMismatch(MethodArgumentTypeMismatchException exception) {
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Not Found!"),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> exceptionResultDataAccess(EmptyResultDataAccessException exception) {
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Unable to find product!"),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConverterNotFoundException.class)
	public ResponseEntity<Object> exceptionConverterNotFound(ConverterNotFoundException exception) {
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> exceptionMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<>(
				new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage()),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> exceptionNoSuchElement(NoSuchElementException exception) {
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "No value present!"),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> exceptionEntityNotFound(EntityNotFoundException exception) {
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Unable to find product!"),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> exceptionMissingServletRequestParameter(
			MissingServletRequestParameterException exception) {
		return new ResponseEntity<>(
				new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Required  parameter  is not present!"),
				HttpStatus.BAD_REQUEST);
	}

}
