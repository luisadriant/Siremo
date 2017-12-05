package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Marca;

@Stateless
public class MarcaDao {
	
	@Inject
	private EntityManager EM;
	
	public void Guardar(Marca m) {
		Marca marca=Leer(m.getId());
		if(marca==null)
			Insertar(m);
		else
			Actualizar(m);
	}
	
	public void Insertar(Marca m) {
		EM.persist(m);
	}
	public void Actualizar(Marca m) {
		EM.merge(m);
	}
	public void Borrar(int id) {
		EM.remove(Leer(id));
	}
	public Marca Leer(int id) {
		Marca marca = EM.find(Marca.class, id);
		return marca;
	}
	//metodo de lista
	public List<Marca> listadomarcas() {
		String  jpql = "SELECT m FROM Marca m";
		Query query = EM.createQuery(jpql, Marca.class);
		List<Marca> marcas = query.getResultList(); 
		return marcas;
	}
	

}
