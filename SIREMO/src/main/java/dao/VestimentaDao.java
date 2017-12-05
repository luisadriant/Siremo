package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Vestimenta;

@Stateless
public class VestimentaDao {
	
	@Inject
	private EntityManager EM;
	
	public void Guardar(Vestimenta v) {
		Vestimenta vestimeta=Leer(v.getId());
		if(vestimeta==null)
			Insertar(v);
		else
			Actualizar(v);
	}
	
	public void Insertar(Vestimenta v) {
		EM.persist(v);
	}
	public void Actualizar(Vestimenta v) {
		EM.merge(v);
	}
	public void Borrar(int id) {
		EM.remove(Leer(id));
	}
	public Vestimenta Leer(int id) {
		Vestimenta vestimeta = EM.find(Vestimenta.class, id);
		return vestimeta;
	}
	//metodo de lista
	public List<Vestimenta> listadovestimentas() {
		String  jpql = "SELECT e FROM Vestimenta e";
		Query query = EM.createQuery(jpql, Vestimenta.class);
		List<Vestimenta> vestimetas = query.getResultList(); 
		return vestimetas;
	}

}
