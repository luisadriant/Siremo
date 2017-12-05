package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import modelo.Administrador;
import modelo.Usuario;

@Stateless
public class AdministradorDAO {

	@Inject
	//@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager EM;
	
	public void Guardar(Administrador a) {
		Administrador admin=Leer(a.getId());
		if(admin==null)
			Insertar(a);
		else
			Actualizar(a);
	}
	
	public void Insertar(Administrador a) {
		EM.persist(a);
	}
	public void Actualizar(Administrador a) {
		EM.merge(a);
	}
	public void Borrar(int id) {
		EM.remove(Leer(id));
	}
	public Administrador Leer(int id) {
		Administrador administrador = EM.find(Administrador.class, id);
		return administrador;
	}
	//metodo de lista
	public List<Administrador> listadoadministradores() {
		String  jpql = "SELECT a FROM Administrador a";
		Query query = EM.createQuery(jpql, Administrador.class);
		List<Administrador> listado = query.getResultList(); 
		return listado;
	}
}
