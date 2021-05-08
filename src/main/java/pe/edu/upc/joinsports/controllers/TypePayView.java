package pe.edu.upc.joinsports.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.joinsports.models.entities.TypePay;
import pe.edu.upc.joinsports.services.TypePayService;
import pe.edu.upc.joinsports.util.Action;

@Named("typePayView")
@ViewScoped
public class TypePayView implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<TypePay> typePays;
	private TypePay typePay;

	private TypePay selectTypePay;

	private Action action;

	// Style for panelGrid and dataTable
	private String stylePanelTypePay;
	private String styleTableTypePay;

	// Disabled utilizado para activar y desactivar los botones
	// Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	// Text in Confirm Dialog
	private String messageConfirmDialog;

	@Inject
	private TypePayService typePayService;

	@PostConstruct
	public void init() {
		cleanForm();
		loadTypePays();
		action = Action.NONE;
	}

	public void loadTypePays() {
		try {
			this.typePays = typePayService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Método que se ejecuta cuando hago click en 'Nuevo'
	public void newTypePay() {
		action = Action.NEW;
		cleanForm();
		this.stateNewEdit();
		addMessage("Creando nuevo Tipo de Pago");
	}

	public void cleanForm() {
		this.typePay = new TypePay();
		this.selectTypePay = null;
	}

	// Metodo para grabar el elemento ingresado en el formulario
	public void saveTypePay() {
		try {
			if (action == Action.NEW) {
				typePayService.save(this.typePay);
				addMessage("Se grabo de forma correcta el nuevo tipo de pago");
			} else if (action == Action.EDIT) {
				typePayService.update(this.typePay);
				addMessage("Se actualizo de forma correcta el tipo de pago");
			}
			cleanForm();
			loadTypePays();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cancelTypePay() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cada vez que el usuario selecciona una fila del
	// datatable
	public void selectTypePay(SelectEvent<TypePay> e) {
		this.selectTypePay = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.selectTypePay.getNameTypePay();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cada vez que el usuario des-selecciona una fila del
	// datatable
	public void unselectTypePay(UnselectEvent<TypePay> e) {
		this.selectTypePay = null;
		this.stateList();
	}

	public void editTypePay() {
		if (this.selectTypePay != null) {
			this.typePay = this.selectTypePay;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteTypePay() {
		if (this.selectTypePay != null) {
			try {
				typePayService.deleteById(this.selectTypePay.getId());
				cleanForm();
				loadTypePays();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// State on Application
	public void stateList() {
		this.stylePanelTypePay = "display:none;";
		this.styleTableTypePay = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelTypePay = "display:block;";
		this.styleTableTypePay = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelTypePay = "display:none;";
		this.styleTableTypePay = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = false;
		this.disabledButtonEliminar = false;
	}

	// Método que muestra los mensajes
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<TypePay> getTypePays() {
		return typePays;
	}
	
	public TypePayService getTypePayService() {
		return typePayService;
	}

	public TypePay getTypePay() {
		return typePay;
	}
	
	public String getStyleTableTypePay() {
		return styleTableTypePay;
	}

	public String getStylePanelTypePay() {
		return stylePanelTypePay;
	}

	public boolean isDisabledButtonNuevo() {
		return disabledButtonNuevo;
	}

	public boolean isDisabledButtonGrabar() {
		return disabledButtonGrabar;
	}

	public boolean isDisabledButtonCancelar() {
		return disabledButtonCancelar;
	}

	public boolean isDisabledButtonEditar() {
		return disabledButtonEditar;
	}

	public boolean isDisabledButtonEliminar() {
		return disabledButtonEliminar;
	}

	public String getMessageConfirmDialog() {
		return messageConfirmDialog;
	}
}
