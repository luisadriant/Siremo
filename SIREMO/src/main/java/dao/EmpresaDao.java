package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import modelo.Empresa;

@Stateless
public class EmpresaDao {
	
	@Inject
	// @PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager EM;
	
	public void Guardar(Empresa e) {
		Empresa empresa=Leer(e.getId());
		if(empresa==null)
			Insertar(e);
		else
			Actualizar(e);
	}
	
	public void Insertar(Empresa e) {
		EM.persist(e);
	}
	public void Actualizar(Empresa e) {
		System.out.println("LLEGA AL METODO DE ACTULIZAR");
		//System.out.println(e.getVestimentas().get(0).getMarca().getNombre());
		EM.merge(e);
	}
	public void Borrar(int id) {
		EM.remove(Leer(id));
	}
	public Empresa Leer(int id) {
		Empresa empresa = EM.find(Empresa.class, id);
		return empresa;
	}
	//metodo de lista
	public List<Empresa> listadoempresas() {
		String  jpql = "SELECT e FROM Empresa e";
		Query query = EM.createQuery(jpql, Empresa.class);
		List<Empresa> empresas = query.getResultList(); 
		return empresas;
	}

}
