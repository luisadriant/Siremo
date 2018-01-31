package edu.ups.ec.siremo.preference;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("m")
@SessionScoped
public class MisPreferences implements Serializable{

	private int idUsuario;
	private int idVestimenta;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdVestimenta() {
		return idVestimenta;
	}
	public void setIdVestimenta(int idVestimenta) {
		this.idVestimenta = idVestimenta;
	}
	
	
	
}
