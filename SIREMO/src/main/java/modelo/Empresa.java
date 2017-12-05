package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sir_empresa", uniqueConstraints = @UniqueConstraint(columnNames = "emp_ruc"))
public class Empresa implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="emp_id")
	private int id;
	
	@Column(name="emp_nombre")
	@NotNull
	private String nombre;
	
	@Column(name="emp_descripcion")
	@NotNull
	private String descripcion;
	
	@Column(name="emp_ruc")
	@NotNull
	private String ruc;
	
	@Column(name="emp_direccion")
	@NotNull
	private String direccion;
	
	@OneToMany(cascade= {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="ves_emp_id", referencedColumnName="emp_id")
	private List<Vestimenta> vestimentas;
	
	
	public List<Vestimenta> getVestimentas() {
		return vestimentas;
	}
	public void setVestimentas(List<Vestimenta> vestimentas) {
		this.vestimentas = vestimentas;
	}
	public int getId() {
		return id;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void addVestimenta(Vestimenta v) {
		if (vestimentas==null)
			vestimentas=new ArrayList<Vestimenta>();
		vestimentas.add(v);
	}
	
	

}