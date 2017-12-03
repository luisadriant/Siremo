package edu.ups.ec.siremo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.ups.ec.siremo.entidades.Empresa;

@Stateless
public class EmpresaDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar (Empresa empresa){
		Empresa e= leer(empresa.getId());
		if(e==null){
			insertar(empresa);
		}else{
			actualizar(empresa);
		}
			
	}
	
	public void insertar(Empresa empresa){
		em.persist(empresa);
		
		
	}
	
	public void actualizar(Empresa empresa){
		em.merge(empresa);
		
	}

	public void borrar(int id){
	em.remove(leer(id));
	
	}

	public Empresa leer(int i){
		Empresa e = em.find(Empresa.class, i);
		return e;

	}
	
	public List <Empresa> listadoEmpresas(){
		String jpql ="SELECT p FROM Empresa e";
		Query query = em.createQuery(jpql, Empresa.class);
		List <Empresa> listado = query.getResultList();
		
		return listado;
	}

}
