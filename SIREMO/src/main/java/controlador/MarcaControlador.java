package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import dao.MarcaDao;
import modelo.Marca;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class MarcaControlador {

	@PostConstruct
	public void init() {
		marca=new Marca();
		loadMarcas();
	}
	
	private Marca marca;
	private List<Marca> marcas;
	private int id;
	
	@Inject
	private MarcaDao MDAO;

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
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
		 MDAO.Guardar(marca);
		 loadMarcas();
		 return "listarMarca";
	}
	public String Eliminar(int id) {
		MDAO.Borrar(id);
		loadMarcas();
		return "listarMarca";
	}
	private void loadMarcas() {
		// TODO Auto-generated method stub
		marcas=MDAO.listadomarcas();
	}
	public String loadDatoseditar(int id) {
		marca = MDAO.Leer(id);
		System.out.println(marca.getId());
		return "editarMarca";
	}
	
}
