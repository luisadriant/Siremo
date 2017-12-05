package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

/**
 * Esta clase es la entidad Usuario que contiene los getters y setters.
 * @author root
 */
@Entity
@DiscriminatorValue(value="Usuario")
public class Usuario extends Persona implements Serializable{

	/**
	 * Esta es la relacion de muchos a muchos entre usuario y vetimenta para escoger sus vestimentas favoritas.
	 */
	@ManyToMany 
	@JoinTable(name="sir_favoritos",joinColumns=@JoinColumn(name="fav_per_id"),inverseJoinColumns=@JoinColumn(name="fav_ves_id"))
	private List<Vestimenta> vestimentas;

	public List<Vestimenta> getVestimentas() {
		return vestimentas;
	}

	public void setVestimentas(List<Vestimenta> vestimentas) {
		this.vestimentas = vestimentas;
	}
	
	
}
