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

import pe.edu.upc.joinsports.models.entities.TypeEvent;
import pe.edu.upc.joinsports.services.TypeEventService;
import pe.edu.upc.joinsports.util.Action;

@Named("typeEventView")
@ViewScoped
public class TypeEventView implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<TypeEvent> typeEvents;
	private TypeEvent typeEvent;

	private TypeEvent selectTypeEvent;

	private Action action;

	// Style for panelGrid and dataTable
	private String stylePanelTypeEvent;
	private String styleTableTypeEvent;

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
	private TypeEventService typeEventService;

	@PostConstruct
	public void init() {
		cleanForm();
		loadTypeEvents();
		action = Action.NONE;
	}

	public void loadTypeEvents() {
		try {
			this.typeEvents = typeEventService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Método que se ejecuta cuando hago click en 'Nuevo'
	public void newTypeEvent() {
		action = Action.NEW;
		cleanForm();
		this.stateNewEdit();
		addMessage("Creando nuevo tipo de evento");
	}

	public void cleanForm() {
		this.typeEvent = new TypeEvent();
		this.selectTypeEvent = null;
	}

	// Metodo para grabar el elemento ingresado en el formulario
	public void saveTypeEvent() {
		try {
			if (action == Action.NEW) {
				typeEventService.save(this.typeEvent);
				addMessage("Se grabo de forma correcta el nuevo tipo de evento");
			} else if (action == Action.EDIT) {
				typeEventService.update(this.typeEvent);
				addMessage("Se actualizo de forma correcta el tipo de evento");
			}
			cleanForm();
			loadTypeEvents();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cancelTypeEvent() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cada vez que el usuario selecciona una fila del
	// datatable
	public void selectTypeEvent(SelectEvent<TypeEvent> e) {
		this.selectTypeEvent = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.selectTypeEvent.getNameTypeEvent();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cada vez que el usuario des-selecciona una fila del
	// datatable
	public void unselectTypeEvent(UnselectEvent<TypeEvent> e) {
		this.selectTypeEvent = null;
		this.stateList();
	}

	public void editTypeEvent() {
		if (this.selectTypeEvent != null) {
			this.typeEvent = this.selectTypeEvent;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteTypeEvent() {
		if (this.selectTypeEvent != null) {
			try {
				typeEventService.deleteById(this.selectTypeEvent.getId());
				cleanForm();
				loadTypeEvents();
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
		this.stylePanelTypeEvent = "display:none;";
		this.styleTableTypeEvent = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelTypeEvent = "display:block;";
		this.styleTableTypeEvent = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelTypeEvent = "display:none;";
		this.styleTableTypeEvent = "display:block;";
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

	public List<TypeEvent> getTypeEvents() {
		return typeEvents;
	}
	
	public TypeEventService getTypeEventService() {
		return typeEventService;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}
	
	public String getStyleTableTypeEvent() {
		return styleTableTypeEvent;
	}

	public String getStylePanelTypeEvent() {
		return stylePanelTypeEvent;
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
