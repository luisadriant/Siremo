package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Esta clase es la entidad Votos que contiene los getters y setters.
 * @author root
 */
@Entity
@Table(name="sir_votos")
public class Votos {
	@Id
	@GeneratedValue
	@Column(name="vot_id")
	private int id;
	
	@NotNull
	@Column(name="vot_voto")
	private int voto;
	
	/*@ManyToOne
	@JoinColumn(name="vot_per_id", referencedColumnName="per_id")
	private Usuario usuario;

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	
	
}
