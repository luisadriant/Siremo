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

import edu.ups.ec.siremo.dao.AdministradorDAO;
import edu.ups.ec.siremo.dao.UsuarioDAO;
import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Empresa;
import edu.ups.ec.siremo.modelo.Usuario;
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
//		private Usuario usuario;
//		//instalcia del administrador
//		private Administrador administrador;
//		//id de un usuario
//		private int id;
//		public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//			loadDatosUsuarioL1(id);
//		}
//		
//		public Usuario getUsuario() {
//			return usuario;
//		}
//		public void setUsuario(Usuario usuario) {
//			this.usuario = usuario;
//		}
//		public Administrador getAdministrador() {
//			return administrador;
//		}
//		public void setAdministrador(Administrador administrador) {
//			this.administrador = administrador;
//		}
//		//este metodo nos sirve para cargar la pagina y editar los datos de Usuario
//		public void loadDatosUsuarioL1(int id) {
//			usuario = UDAO.Leer(id);
//			administrador=ADAO.Leer(id);
//			System.out.println(administrador.getApellidos()+"<<<<<<<<-");
//			//return "";
//		}
//	
	//-----------------------------------------------------------------
	
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
					//setId(usuarios.get(0).getId());
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
					externalContext.redirect("principal_face.xhtml?idUsuario="+usuarios.get(0).getId());
					return "";
				}else {
					administradores=ADAO.listadoadministradoresLog(nombreusuario, password);
					if(administradores.size()>0) {
						//setId(administradores.get(0).getId());
						ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
						externalContext.redirect("misEmpresas_face.xhtml?id="+administradores.get(0).getId());
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
