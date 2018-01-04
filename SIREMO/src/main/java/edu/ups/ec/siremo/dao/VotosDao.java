package edu.ups.ec.siremo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.ups.ec.siremo.modelo.Votos;

@Stateless
public class VotosDao {

	//llamamos al entity manager el cual nos permite la conexion con la BD y poder realizar las persistencias
	@Inject
	private EntityManager EM;
	
	
	//este metodo nos permite guardar la persistencia, en caso de que exista actualiza sus datos
		public void Guardar(Votos v) {
			Votos voto=Leer(v.getId());
			if(voto==null)
				Insertar(v);
			else
				Actualizar(v);
		}
		
		public void Insertar(Votos m) {
			EM.persist(m);
		}
		public void Actualizar(Votos m) {
			EM.merge(m);
		}
		public void Borrar(int id) {
			EM.remove(Leer(id));
		}
		//este metodo nos sirve para buscar una Marca en base a un id
		public Votos Leer(int id) {
			Votos voto = EM.find(Votos.class, id);
			return voto;
		}
		//Este metodo nos sirve para hacer un select y obtener todo las marcas
		public List<Votos> listadoVotos() {
			String  jpql = "SELECT v FROM Votos v";
			Query query = EM.createQuery(jpql, Votos.class);
			List<Votos> votos= query.getResultList(); 
			return votos;
		}
		


	
}
