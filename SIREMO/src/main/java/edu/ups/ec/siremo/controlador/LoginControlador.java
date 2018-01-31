package edu.ups.ec.siremo.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.ups.ec.siremo.dao.AdministradorDao;
import edu.ups.ec.siremo.dao.UsuarioDao;
import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Empresa;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.preference.MisPreferences;
import edu.ups.ec.siremo.util.ErrorsController;

@ManagedBean
@SessionScoped
public class LoginControlador {
	
	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
    private FacesContext facesContext;
	
	//variables necesarias
	private List<Usuario> usuarios;
	//variables necesarias
	private List<Administrador> administradores;
	//variable para el nombre de usuario
	private String nombreusuario;
	//variable para el password
	private String password;
	//variable de verificacion
	private boolean datosIncorrectos;

	
	//-------------------------------------------------
//		//instancia de la entidad de negocio Usuario
		private Usuario usuario;

		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Usuario
		@Inject
		private UsuarioDao usuarioDAO;
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al administra 
		@Inject
		private AdministradorDao administradoDAO;
		
		@Inject
		private MisPreferences preferences;
	
		
	
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
				usuarios=usuarioDAO.listadousuarioLog(nombreusuario, password);
				if(usuarios.size()>0) {
					System.out.println("entra aqui usuarios");
					usuario = usuarioDAO.Leer(usuarios.get(0).getId());
//					setId(usuarios.get(0).getId());
					
					//condicion para saber si ya realizo el test y tiene asigando un estilo
					if(usuario.getEstilo()!=null) {
						
						preferences.setIdUsuario(usuario.getId());
						
					//es necesario para redireccionar y enviar los parametros a la otra clase
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				    externalContext.redirect("principal_face.xhtml");
					}else {
						preferences.setIdUsuario(usuario.getId());
						
						ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
					    externalContext.redirect("TestEstiloModa.xhtml");						
					}
					return "";
				}else {
					administradores=administradoDAO.listadoadministradoresLog(nombreusuario, password);
					if(administradores.size()>0) {
						preferences.setIdUsuario(administradores.get(0).getId());
						ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
						externalContext.redirect("misEmpresas_face.xhtml");
						return "";
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
