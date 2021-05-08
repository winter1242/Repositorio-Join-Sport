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

import pe.edu.upc.joinsports.models.entities.Sport;
import pe.edu.upc.joinsports.services.SportService;
import pe.edu.upc.joinsports.util.Action;

@Named("sportView")
@ViewScoped
public class SportView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Sport> sports;
	private Sport sport;

	private Sport selectSport;

	private Action action;

	// Style for panelGrid and dataTable
	private String stylePanelSport;
	private String styleTableSport;

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
	private SportService sportService;

	@PostConstruct
	public void init() {
		cleanForm();
		loadSports();
		action = Action.NONE;
	}

	public void loadSports() {
		try {
			this.sports = sportService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Método que se ejecuta cuando hago click en 'Nuevo'
	public void newSport() {
		action = Action.NEW;
		cleanForm();
		this.stateNewEdit();
		addMessage("Creando nuevo Deporte");
	}

	public void cleanForm() {
		this.sport = new Sport();
		this.selectSport = null;
	}

	// Metodo para grabar el elemento ingresado en el formulario
	public void saveSport() {
		try {
			if (action == Action.NEW) {
				sportService.save(this.sport);
				addMessage("Se grabo de forma correcta el nuevo deporte");
			} else if (action == Action.EDIT) {
				sportService.update(this.sport);
				addMessage("Se actualizo de forma correcta el deporte");
			}
			cleanForm();
			loadSports();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cancelSport() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cada vez que el usuario selecciona una fila del
	// datatable
	public void selectSport(SelectEvent<Sport> e) {
		this.selectSport = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.selectSport.getNameSport();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cada vez que el usuario des-selecciona una fila del
	// datatable
	public void unselectSport(UnselectEvent<Sport> e) {
		this.selectSport = null;
		this.stateList();
	}

	public void editSport() {
		if (this.selectSport != null) {
			this.sport = this.selectSport;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteSport() {
		if (this.selectSport != null) {
			try {
				sportService.deleteById(this.selectSport.getId());
				cleanForm();
				loadSports();
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
		this.stylePanelSport = "display:none;";
		this.styleTableSport = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelSport = "display:block;";
		this.styleTableSport = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelSport = "display:none;";
		this.styleTableSport = "display:block;";
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

	public List<Sport> getSports() {
		return sports;
	}
	
	public SportService getSportService() {
		return sportService;
	}

	public Sport getSport() {
		return sport;
	}
	
	public String getStyleTableSport() {
		return styleTableSport;
	}

	public String getStylePanelSport() {
		return stylePanelSport;
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
