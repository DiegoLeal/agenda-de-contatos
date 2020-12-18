package com.agenda.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.agenda.config.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Contato.class)
	private Long id;
	
	@ManyToOne
	@JsonView(Views.Contato.class)
	private Usuario usuario;
	
	@JsonView(Views.Contato.class)
	private String nome;
	
	@JsonView(Views.Contato.class)
	private String email;
	
	@JsonView(Views.Contato.class)
	private String contatoCel;		
	
	public Contato() {
	}
		
}
