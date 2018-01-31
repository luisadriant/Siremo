package edu.ups.ec.siremo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Esta clase es la entidad Votos que contiene los getters y setters.
 * @author root
 */
@Entity
@Table(name="sir_comentarios")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="com_id")
	private int id;
	
	@NotNull
	@Column(name="com_comentario")
	private String comentarios;
	
	@ManyToOne
	@JoinColumn(name="com_per_id", referencedColumnName="per_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="com_vest_id", referencedColumnName="ves_id")
	private Vestimenta vestimenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vestimenta getVestimenta() {
		return vestimenta;
	}

	public void setVestimenta(Vestimenta vestimenta) {
		this.vestimenta = vestimenta;
	}

	

	
}