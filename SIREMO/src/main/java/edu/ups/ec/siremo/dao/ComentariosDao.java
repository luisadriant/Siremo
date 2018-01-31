package edu.ups.ec.siremo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.ups.ec.siremo.modelo.Comentario;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.modelo.Vestimenta;
import edu.ups.ec.siremo.modelo.Votos;
import edu.ups.ec.siremo.modelo.Comentario;

@Stateless
public class ComentariosDao {

	//llamamos al entity manager el cual nos permite la conexion con la BD y poder realizar las persistencias
	@Inject
	private EntityManager EM;
	
	
		
		public void Insertar(Comentario m) {
			EM.persist(m);
		}
		public void Actualizar(Comentario m) {
			EM.merge(m);
		}
		public void Borrar(int id) {
			EM.remove(Leer(id));
		}
		//este metodo nos sirve para buscar una Marca en base a un id
		public Comentario Leer(int id) {
			Comentario comentario = EM.find(Comentario.class, id);
			return comentario;
		}
		
		
		public List<Comentario> listadoComentario(Vestimenta ves) {
			String  jpql = "SELECT c FROM Comentario c WHERE c.vestimenta= :vesti";
			Query query = EM.createQuery(jpql, Comentario.class);
			query.setParameter("vesti",ves);
			
			List<Comentario> Comentario= query.getResultList(); 
			return Comentario;
		}
		
		
		
	
}
