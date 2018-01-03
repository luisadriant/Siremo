package edu.ups.ec.siremo.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import javax.faces.model.SelectItem;

import javax.inject.Inject;

import edu.ups.ec.siremo.dao.EmpresaDao;
import edu.ups.ec.siremo.dao.MarcaDao;
import edu.ups.ec.siremo.modelo.Empresa;
import edu.ups.ec.siremo.modelo.Marca;
import edu.ups.ec.siremo.modelo.Vestimenta;
import edu.ups.ec.siremo.util.ErrorsController;

/**
 * Esta clase sirve para enlazar la vista del archivo xhtml con el controlador de la Empresa
 * @author root
 *
 */
@ManagedBean
@ViewScoped
public class EmpresaControlador {
	

	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
	private FacesContext facesContext;

	private Empresa empresa;
	//variables necesarias
	private List<Empresa> empresas;

	private List<Marca> marcas;
	private List<SelectItem> camposMarcas;
	private int id_marca;
	private Marca marca;

	private int id;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Empresa
	@Inject
	private EmpresaDao EDAO;

  @Inject
	private MarcaDao MDAO;

	
	//este metodo se inicia cada vez que se construye la clase
		@PostConstruct
	public void init() {
		empresa=new Empresa();
		marca=new Marca();
		empresa.addVestimenta(new Vestimenta());
		loadEmpresas();
		loadMarcas();
		
	}
	
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
	//este metodo nos sirve para guardar una Empresa 
	public String Guardar() {

    try {
		 for (int i=0 ; i<empresa.getVestimentas().size(); i++) {
			 if(empresa.getVestimentas().get(i).getId_marca() != 0) {
				 loadDatosMarca(empresa.getVestimentas().get(i).getId_marca());
				 empresa.getVestimentas().get(i).addMarca(marca);
			 } 
		 }
		 EDAO.Guardar(empresa);
		 loadEmpresas();
      }catch (Exception e) {
		String errorMessage = error.getRootErrorMessage(e);
	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	    facesContext.addMessage(null, m);
		}
		 return "listaAdministrador";

	}
	//este metodo nos sirve para eliminar una Empresa y a la vez mostra en pantalla las empresas que sobran
	public String Eliminar(int id) {
		EDAO.Borrar(id);
		loadEmpresas();
		return null;
	}
	//este metodo nos sirve para cargar los datos de todos las Empresas que existen en la BD
	private void loadEmpresas() {
		empresas=EDAO.listadoempresas();
		
	}
	private void loadMarcas() {
		// TODO Auto-generated method stub
		marcas=MDAO.listadomarcas();
	}
	//este metodo nos sirve para cargar la pagina y editar los datos de la empresa
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
	//este metodo nos sirve para cargar la pagina y editar los datos de empresa
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
