package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import dao.EmpresaDao;
import dao.MarcaDao;
import modelo.Empresa;
import modelo.Marca;
import modelo.Vestimenta;

@ManagedBean
@ViewScoped
public class EmpresaControlador {
	
	@PostConstruct
	public void init() {
		empresa=new Empresa();
		empresa.addVestimenta(new Vestimenta());
		loadEmpresas();
	}
	private Empresa empresa;
	private List<Empresa> empresas;
	//private int id_marca;
	//private Marca marca;
	private int id;
	
	@Inject
	private EmpresaDao EDAO;

	/*@Inject
	private MarcaDao MDAO;
	
	
	public int getId_marca() {
		return id_marca;
	}

	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}*/

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatoseditar(id);
	}
	public String Guardar() {
		 System.out.println("hola "+empresa.getNombre());
		 EDAO.Guardar(empresa);
		 loadEmpresas();
		 return null;
	}
	public String Eliminar(int id) {
		EDAO.Borrar(id);
		loadEmpresas();
		return null;
	}
	private void loadEmpresas() {
		// TODO Auto-generated method stub
		empresas=EDAO.listadoempresas();
	}
	public String loadDatoseditar(int id) {
		empresa = EDAO.Leer(id);
		return "editarEmpresa";
	}
	public String addVestimenta() {
		empresa.addVestimenta(new Vestimenta());
		return null;
	}
	/*public Marca agregarMarca() {
		 marca=MDAO.Leer(4);
		 return marca;
	}*/
	
	
	
}
