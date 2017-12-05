package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import dao.UsuarioDAO;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioControlador {
	
	@PostConstruct
	public void init() {
		usuario=new Usuario();
		loadUsuarios();
	}
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private int id;
	
	@Inject
	private UsuarioDAO UDAO;
	
	
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
	
	public String Guardar() {
		 System.out.println("hola");
		 UDAO.Guardar(usuario);
		 loadUsuarios();
		 return null;
	}
	public String Eliminar(int id) {
		UDAO.Borrar(id);
		loadUsuarios();
		return "listaUsuario";
	}
	private void loadUsuarios() {
		// TODO Auto-generated method stub
		usuarios=UDAO.listadousuarios();
	}
	public String loadDatoseditar(int id) {
		usuario = UDAO.Leer(id);
		System.out.println(usuario.getId());
		return "editarUsuario";
	}
	
}
