package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import dao.VestimentaDao;
import edu.ups.ec.siremo.util.ErrorsController;
import modelo.Vestimenta;

/**
 * Esta clase sirve para enlazar la vista del archivo xhtml con el controlador de la vestimenta 
 * @author root
 *
 */
@ManagedBean
@ViewScoped
public class VestimentaControlador {
	
	// Instanciamos la clase que controla los errores con su respectivo inject.
	ErrorsController error = new ErrorsController();
	@Inject
	private FacesContext facesContext;	
	
	//instancia de la entidad de negocio Vestimenta
	private Vestimenta vestimenta;
	//variables necesarias
	private List<Vestimenta> vestimentas;
	private int id;
	
	//instanciamos en objeto de acceso a datos para poder injectar los metodos crud correspondiente al Vestimenta
	@Inject
	private VestimentaDao VDAO;

	//este metodo se inicia cada vez que se construye la clase
		@PostConstruct
		public void init() {
			vestimenta=new Vestimenta();
			loadVestimentas();
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
		loadDatoseditar(id);
	}
	
	//este metodo nos sirve para guardar una Vestimenta 
	public String Guardar() {
		try {
		 VDAO.Guardar(vestimenta);
		 loadVestimentas();
		 
		}catch (Exception e) {
			String errorMessage = error.getRootErrorMessage(e);
		    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
		    facesContext.addMessage(null, m);
		}
		 return null;
	}
	//este metodo nos sirve para eliminar una Vestimenta y a la vez mostra en pantalla las vestimentas que sobran
	public String Eliminar(int id) {
		VDAO.Borrar(id);
		loadVestimentas();
		return "listaUsuario";
	}
	//este metodo nos sirve para cargar los datos de todas las Vestimentas que existen en la BD
	private void loadVestimentas() {
		// TODO Auto-generated method stub
		vestimentas=VDAO.listadovestimentas();
	}
	//este metodo nos sirve para cargar la pagina y editar los datos de Vestimentas
	public String loadDatoseditar(int id) {
		vestimenta = VDAO.Leer(id);
		System.out.println(vestimenta.getId());
		return "editarUsuario";
	}
	
	
}
