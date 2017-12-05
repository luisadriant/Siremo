package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import dao.EmpresaDao;
import dao.MarcaDao;
import edu.ups.ec.siremo.util.ErrorsController;
import modelo.Empresa;
import modelo.Marca;
import modelo.Vestimenta;

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
	
	//instancia de la entidad de negocio Empresa
	private Empresa empresa;
	//variables necesarias
	private List<Empresa> empresas;
	private int id;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Empresa
	@Inject
	private EmpresaDao EDAO;

	
	//este metodo se inicia cada vez que se construye la clase
		@PostConstruct
		public void init() {
			empresa=new Empresa();
			empresa.addVestimenta(new Vestimenta());
			loadEmpresas();
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
		 EDAO.Guardar(empresa);
		 loadEmpresas();
		 
		}catch (Exception e) {
		String errorMessage = error.getRootErrorMessage(e);
	    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	    facesContext.addMessage(null, m);
		}
		 return null;
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
	//este metodo nos sirve para cargar la pagina y editar los datos de la empresa
	public String loadDatoseditar(int id) {
		empresa = EDAO.Leer(id);
		return "editarEmpresa";
	}
	//este metodo nos sirve para cargar la pagina y editar los datos de empresa
	public String addVestimenta() {
		empresa.addVestimenta(new Vestimenta());
		return null;
	}
	
	
}
