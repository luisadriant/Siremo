package edu.ups.ec.siremo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.ups.ec.siremo.dao.AdministradorDao;
import edu.ups.ec.siremo.dao.ComentariosDao;
import edu.ups.ec.siremo.dao.UsuarioDao;
import edu.ups.ec.siremo.dao.VestimentaDao;
import edu.ups.ec.siremo.modelo.Administrador;
import edu.ups.ec.siremo.modelo.Comentario;
import edu.ups.ec.siremo.modelo.Usuario;
import edu.ups.ec.siremo.modelo.Vestimenta;


@Path("/comentario")
public class ComentarioService {
	
	@Inject
	private VestimentaDao vestimentaDao;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	
	@Inject
	private ComentariosDao comentarioDao;
	
	
	@GET
	@Path("/getcomentarios")
	@Produces("application/json")
	public List<Comentario> getComentarios(@QueryParam("idVestimenta") int idVestimenta) {
		
		
		Vestimenta ves = vestimentaDao.Leer(idVestimenta);
		
		
		List<Comentario> listaComentarios = comentarioDao.listadoComentario(ves);
		
		for (Comentario comentario : listaComentarios) {
			comentario.getVestimenta().setEmpresa(null);
		}
		
		return listaComentarios;
	}
	
	@POST
	@Path("/setcomentarios")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta setComentarios(@QueryParam("idVestimenta") int idVestimenta, @QueryParam("idUsuario") int idUsuario, Respuesta respuesta) {
		
		System.out.println("LEGGO EL OMNETARIOS");
		Usuario usu = usuarioDao.Leer(idUsuario);
		
		Vestimenta ves = vestimentaDao.Leer(idVestimenta);
		
		Comentario com = new Comentario();
		com.setUsuario(usu);
		com.setVestimenta(ves);
		
		com.setComentarios(respuesta.getMensaje());
		
		comentarioDao.Insertar(com);
		
		Respuesta resp = new Respuesta();
		resp.setCodigo(1);
		resp.setMensaje("Todo bien");
		
		
		return resp;
	}
	
}
