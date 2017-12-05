package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import dao.VestimentaDao;
import modelo.Vestimenta;

@ManagedBean
@ViewScoped
public class VestimentaControlador {
	
	@PostConstruct
	public void init() {
		vestimenta=new Vestimenta();
		loadVestimentas();
	}
	
	private Vestimenta vestimenta;
	private List<Vestimenta> vestimentas;
	private int id;
	
	@Inject
	private VestimentaDao VDAO;

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
		loadDatoseditar(id);
	}
	public String Guardar() {
		 System.out.println("hola");
		 VDAO.Guardar(vestimenta);
		 loadVestimentas();
		 return null;
	}
	public String Eliminar(int id) {
		VDAO.Borrar(id);
		loadVestimentas();
		return "listaUsuario";
	}
	private void loadVestimentas() {
		// TODO Auto-generated method stub
		vestimentas=VDAO.listadovestimentas();
	}
	public String loadDatoseditar(int id) {
		vestimenta = VDAO.Leer(id);
		System.out.println(vestimenta.getId());
		return "editarUsuario";
	}
	
	
}
