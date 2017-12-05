package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

/**
 * Esta clase nos sirve para realizar todos los metodos crud y poder interactuar con la BD
 * @author root
 */
@Stateless
public class UsuarioDAO {
	
	//llamamos al entity manager el cual nos permite la conexion con la BD y poder realizar las persistencias
	@Inject
	private EntityManager EM;
	
	//este metodo nos permite guardar la persistencia, en caso de que exista actualiza sus datos
	public void Guardar(Usuario u) {
		Usuario usr=Leer(u.getId());
		if(usr==null)
			Insertar(u);
		else
			Actualizar(u);
	}
	
	public void Insertar(Usuario u) {
		EM.persist(u);
	}
	public void Actualizar(Usuario u) {
		EM.merge(u);
	}
	public void Borrar(int id) {
		EM.remove(Leer(id));
	}
	//este metodo nos sirve para buscar un Usuario en base a un id
	public Usuario Leer(int id) {
		Usuario usuario = EM.find(Usuario.class, id);
		return usuario;
	}
	//Este metodo nos sirve para hacer un select y obtener todo los usuarios
		public List<Usuario> listadousuarios() {
			String  jpql = "SELECT u FROM Usuario u";
			Query query = EM.createQuery(jpql, Usuario.class);
			List<Usuario> listado = query.getResultList(); 
			return listado;
		}
}
