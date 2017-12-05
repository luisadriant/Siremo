package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

@Stateless
public class UsuarioDAO {
	
	@Inject
	private EntityManager EM;
	
	
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
	public Usuario Leer(int id) {
		Usuario usuario = EM.find(Usuario.class, id);
		return usuario;
	}
	//metodo de lista
		public List<Usuario> listadousuarios() {
			String  jpql = "SELECT u FROM Usuario u";
			Query query = EM.createQuery(jpql, Usuario.class);
			List<Usuario> listado = query.getResultList(); 
			return listado;
		}
}
