package com.agenda.controller.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleNegocio(Exception ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var mensagemErro = new MensagemErro();
		mensagemErro.setStatus(status.value());
		mensagemErro.setTitulo(ex.getMessage());
		mensagemErro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, mensagemErro, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var campos = new ArrayList<MensagemErro.Campo>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new MensagemErro.Campo(nome, mensagem));
		}
		
		var mensagemErro = new MensagemErro();
		mensagemErro.setStatus(status.value());
		mensagemErro.setTitulo("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente");		
		mensagemErro.setDataHora(OffsetDateTime.now());
		mensagemErro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, mensagemErro, headers, status, request);
	}
	
}
