package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sir_marca", uniqueConstraints = @UniqueConstraint(columnNames = "mar_nombre"))
public class Marca implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="mar_id")
	private int id;
	
	@NotNull
	@Column(name="mar_nombre")
	private String nombre;
	
	public int getId() {
		return id;
		//get del id de la marca
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
