package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import dao.AdministradorDAO;
import modelo.Administrador;
import modelo.Empresa;

@ManagedBean
@ViewScoped
public class AdministradorControlador {
	
	@PostConstruct
	public void init() {
		administrador=new Administrador();
		administrador.addEmpresa(new Empresa());
		loadAdministradores();
	}
	
	private Administrador administrador;
	private List<Administrador> administradores;
	private int id;
	
	@Inject
	private AdministradorDAO ADAO;

	
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatoseditar(id);
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	public String Guardar() {
		 System.out.println("hola");
		 ADAO.Guardar(administrador);
		 loadAdministradores();
		 return null;
	}
	public String Eliminar(int id) {
		ADAO.Borrar(id);
		loadAdministradores();
		return null;
	}
	private void loadAdministradores() {
		// TODO Auto-generated method stub
		administradores=ADAO.listadoadministradores();
	}
	public String loadDatoseditar(int id) {
		administrador = ADAO.Leer(id);
		return "editarAdministrador";
	}
	public String addEmpresa() {
		administrador.addEmpresa(new Empresa());
		return null;
	}

}
