package edu.ups.ec.siremo.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.ups.ec.siremo.dao.UsuarioDAO;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.util.ErrorsController;

/**
 * Esta clase sirve para enlazar la vista del archivo xhtml con el controlador del usuario 
 * @author root
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioControlador {
	
	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
	private FacesContext facesContext;
	
	//instancia de la entidad de negocio Usuario
	private Usuario usuario;
	//variables necesarias
	private List<Usuario> usuarios;
	private int id;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Usuario
	@Inject
	private UsuarioDAO UDAO;
	
	//este metodo se inicia cada vez que se construye la clase
		@PostConstruct
		public void init() {
			usuario=new Usuario();
			loadUsuarios();
		}
		
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		loadDatoseditar(id);
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	//este metodo nos sirve para guardar un Usuario 
	public String Guardar() {
		try { 
		UDAO.Guardar(usuario);
		 loadUsuarios();

		
	}catch (Exception e) {
		String errorMessage = error.getRootErrorMessage(e);
	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	    facesContext.addMessage(null, m);
	}
		 return "listaUsuario";
	}
	//este metodo nos sirve para eliminar un Usuario y a la vez mostra en pantalla los usuarios que sobran 
	public String Eliminar(int id) {
		UDAO.Borrar(id);
		loadUsuarios();
		return "listaUsuario";
	}
	//este metodo nos sirve para cargar los datos de todos los Usuarios que existen en la BD
	private void loadUsuarios() {
		usuarios=UDAO.listadousuarios();
	}
	//este metodo nos sirve para cargar la pagina y editar los datos de Usuario
	public String loadDatoseditar(int id) {
		usuario = UDAO.Leer(id);
		return "editarUsuario";
	}
	
	
}
