package edu.ups.ec.siremo.modelo;

import java.util.Hashtable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sir_prediccionesfcm")
public class PrediccionesFCM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cal_id")
	private int id;
	
	@NotNull
	@Column(name= "cal_usuario")
	private int usuario;
	
	@NotNull
	@Column(name= "cal_item")
	private int item;
	
	@NotNull
	@Column(name= "cal_prediccion")
	private int prediccion;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getPrediccion() {
		return prediccion;
	}

	public void setPrediccion(int prediccion) {
		this.prediccion = prediccion;
	}

	
	
}
