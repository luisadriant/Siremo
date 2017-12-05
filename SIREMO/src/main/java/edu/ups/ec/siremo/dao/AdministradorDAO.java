package edu.ups.ec.siremo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Usuario;

/**
 * Esta clase nos sirve para realizar todos los metodos crud y poder interactuar con la BD
 * @author root
 */
@Stateless
public class AdministradorDAO {

	//llamamos al entity manager el cual nos permite la conexion con la BD y poder realizar las persistencias
	@Inject
	private EntityManager EM;
	
	//este metodo nos permite guardar la persistencia, en caso de que exista actualiza sus datos
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
	//este metodo nos sirve para buscar un Administrador en base a un id
	public Administrador Leer(int id) {
		Administrador administrador = EM.find(Administrador.class, id);
		return administrador;
	}
	//Este metodo nos sirve para hacer un select y obtener todo los administradores
	public List<Administrador> listadoadministradores() {
		String  jpql = "SELECT a FROM Administrador a";
		Query query = EM.createQuery(jpql, Administrador.class);
		List<Administrador> listado = query.getResultList(); 
		return listado;
	}
}
