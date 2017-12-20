package edu.ups.ec.siremo.controlador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.ups.ec.siremo.dao.AdministradorDAO;
import edu.ups.ec.siremo.dao.UsuarioDAO;
import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.util.ErrorsController;

@ManagedBean
@ViewScoped
public class LoginControlador {
	
	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
    private FacesContext facesContext;
	//instancia de la entidad de negocio Usuario
	private Usuario usuario;
	//variables necesarias
	private List<Usuario> usuarios;
	//id de un usuario
	private int id;
	//instancia de la entidad de negocio Administrador
	private Administrador administrador;
	//variables necesarias
	private List<Administrador> administradores;
	//variable para el nombre de usuario
	private String nombreusuario;
	//variable para el password
	private String password;
	//variable de verificacion
	private boolean datosIncorrectos;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Usuario
		@Inject
		private UsuarioDAO UDAO;
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al administra 
		@Inject
		private AdministradorDAO ADAO;
	
	
	public boolean isDatosIncorrectos() {
		return datosIncorrectos;
	}
	public void setDatosIncorrectos(boolean datosIncorrectos) {
		this.datosIncorrectos = datosIncorrectos;
	}
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String Iniciar() {	
		try {
			datosIncorrectos=false;
			if(nombreusuario!=null && password!=null) {
				usuarios=UDAO.listadousuarioLog(nombreusuario, password);
				if(usuarios.size()>0) {
					return "principal";
				}else {
					administradores=ADAO.listadoadministradoresLog(nombreusuario, password);
					if(administradores.size()>0) {
						return "misEmpresas_face.xhtml?id="+administradores.get(0).getId();
					}else datosIncorrectos=true;
					
				}
			}	
		}catch (Exception e) {
				String errorMessage = error.getRootErrorMessage(e);
			    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			    facesContext.addMessage(null, m);
		}
		return "";
	}
	

}
