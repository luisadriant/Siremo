package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import dao.UsuarioDAO;
import dao.VestimentaDao;
import modelo.Usuario;
import modelo.Vestimenta;
import modelo.Votos;

@ManagedBean
@ViewScoped
public class VestimentaControlador {
	
	@PostConstruct
	public void init() {
		vestimenta=new Vestimenta();
		usuario=new Usuario();
		//loadVestimentas();
	}
	
	private Usuario usuario;
	private Vestimenta vestimenta;
	private List<Vestimenta> vestimentas;
	private List<SelectItem> camposVotos;
	private List<Votos> votos;
	private int id;
	
	@Inject
	private VestimentaDao VDAO;
	
	@Inject
	private UsuarioDAO UDAO;
	

	
	public List<SelectItem> getCamposVotos() {
		loadCamposVotos();
		return camposVotos;
	}

	public void setCamposVotos(List<SelectItem> camposVotos) {
		this.camposVotos = camposVotos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vestimenta getVestimenta() {
		return vestimenta;
	}

	public void setVestimenta(Vestimenta vestimenta) {
		this.vestimenta = vestimenta;
	}

	public List<Vestimenta> getVestimentas() {
		return vestimentas;
	}

	public void setVestimentas(List<Vestimenta> vestimentas) {
		this.vestimentas = vestimentas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadVestimentas();
		loadDatoUsuario(id);
	}
	public String Guardar() {
		loadDatoUsuario(id);
		 for (int i=0; i< vestimentas.size(); i++) {
			 
				 Votos v=new Votos();
				 v.setUsuario(usuario);
				 v.setVoto(vestimentas.get(i).getVoto());
				 if(vestimentas.get(i).getVotos()==null) 
				 vestimentas.get(i).setVotos(new ArrayList<Votos>());
				 vestimentas.get(i).getVotos().add(v);
				 VDAO.Guardar(vestimentas.get(i));

		 }		
		 loadVestimentas();
		 return "listaUsuariosVotar";
	}
	public String Eliminar(int id) {
		VDAO.Borrar(id);
		loadVestimentas();
		return "listaUsuario";
	}
	private void loadVestimentas() {
		// TODO Auto-generated method stub
		vestimentas=VDAO.listadovestimentas();
		loadDatoUsuario(id);
		 for (int i=0; i< vestimentas.size(); i++) {
			 for(int j=0; j< vestimentas.get(i).getVotos().size(); j++) {
				 if(vestimentas.get(i).getVotos().get(j)!=null) {
				 if(vestimentas.get(i).getVotos().get(j).getUsuario().getId()==usuario.getId()) {
					 //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++"+vestimentas.get(i).getVotos().get(j).getUsuario().getId());
					 vestimentas.get(i).setVoto(vestimentas.get(i).getVotos().get(j).getVoto());
				 }
				 }
			 }
		 }
	}
	public String loadDatoseditar(int id) {
		vestimenta = VDAO.Leer(id);
		System.out.println(vestimenta.getId());
		return "editarUsuario";
	}
	public void loadDatoUsuario(int id) {
		usuario = UDAO.Leer(id);
	}
	private void loadCamposVotos() {
		camposVotos=new ArrayList<SelectItem>();
		camposVotos.add(new SelectItem("1","1"));
		camposVotos.add(new SelectItem("2","2"));
		camposVotos.add(new SelectItem("3","3"));
		camposVotos.add(new SelectItem("4","4"));
		camposVotos.add(new SelectItem("5","5"));
		camposVotos.add(new SelectItem("0","0"));
	}
	
	
}
