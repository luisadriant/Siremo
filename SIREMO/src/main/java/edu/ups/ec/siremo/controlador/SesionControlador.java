package edu.ups.ec.siremo.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.ups.ec.siremo.dao.AdministradorDAO;
import edu.ups.ec.siremo.dao.UsuarioDAO;
import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Usuario;

@ManagedBean
@SessionScoped
public class SesionControlador {
	
	//instancia de la entidad de negocio Usuario
	private Usuario usuario;
	//instalcia del administrador
	private Administrador administrador;
	//id de un usuario
	private int id=2;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Usuario
		@Inject
		private UsuarioDAO UDAO;
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Usuario
		@Inject
		private AdministradorDAO ADAO;
		
		@PostConstruct
		public void init() {
			usuario=new Usuario();
			//loadDatosUsuarioL(id);
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
			//loadDatosUsuarioL(id);
		}
		
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		public Administrador getAdministrador() {
			return administrador;
		}
		public void setAdministrador(Administrador administrador) {
			this.administrador = administrador;
		}
		//este metodo nos sirve para cargar la pagina y editar los datos de Usuario
		public String loadDatosUsuarioL(int id) {
			usuario = UDAO.Leer(id);
			administrador=ADAO.Leer(id);
			//System.out.println(administrador.getApellidos()+"<<<<<<<<");
			return "";
		}

}
