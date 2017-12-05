package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
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
		marca=new Marca();
		empresa.addVestimenta(new Vestimenta());
		loadEmpresas();
		loadMarcas();
		
	}
	private Empresa empresa;
	private List<Empresa> empresas;
	private List<Marca> marcas;
	private List<SelectItem> camposMarcas;
	private int id_marca;
	private Marca marca;
	private int id;
	
	@Inject
	private EmpresaDao EDAO;

	@Inject
	private MarcaDao MDAO;
	
	
	
	public List<SelectItem> getCamposMarcas() {
		loadCamposMarcas();
		return camposMarcas;
	}


	public void setCamposMarcas(List<SelectItem> camposMarcas) {
		this.camposMarcas = camposMarcas;
	}


	public List<Marca> getMarcas() {
		return marcas;
	}


	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}


	public int getId_marca() {
		return id_marca;
	}

	
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
		loadDatosMarca(id_marca);
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

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
		 for (int i=0 ; i<empresa.getVestimentas().size(); i++) {
			 if(empresa.getVestimentas().get(i).getId_marca() != 0) {
				 loadDatosMarca(empresa.getVestimentas().get(i).getId_marca());
				 empresa.getVestimentas().get(i).addMarca(marca);
			 } 
		 }
		 EDAO.Guardar(empresa);
		 loadEmpresas();
		 return "listaAdministrador";
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
	private void loadMarcas() {
		// TODO Auto-generated method stub
		marcas=MDAO.listadomarcas();
	}
	public String loadDatoseditar(int id) {
		empresa = EDAO.Leer(id);
		for (int i=0 ; i<empresa.getVestimentas().size(); i++) {
			 if(empresa.getVestimentas().get(i).getId_marca() == 0) {
				 empresa.getVestimentas().get(i).setId_marca(empresa.getVestimentas().get(i).getMarca().getId());
			 } 
		}
		
		return "editarEmpresa";
	}
	public String loadDatosMarca(int id) {
		marca = MDAO.Leer(id);
		return "editarEmpresa";
	}
	public String addVestimenta() {
		empresa.addVestimenta(new Vestimenta());
		return null;
	}
	private void loadCamposMarcas() {
		loadMarcas();
		camposMarcas=new ArrayList<SelectItem>();
		 for (int i=0 ; i<marcas.size(); i++) {
			 camposMarcas.add(new SelectItem(marcas.get(i).getId()+"",marcas.get(i).getNombre()+""));
		 }
	}

}
