package pe.edu.upc.joinsports.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.joinsports.models.entities.Organizer;
import pe.edu.upc.joinsports.services.OrganizerService;
import pe.edu.upc.joinsports.util.Action;

@Named("organizerView")
@ViewScoped
public class OrganizerView implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Organizer> organizers;
	// Objeto asociado al formulario del organizador
	private Organizer organizer;
	// Objeto asociado al rowSelect del datatable
	private Organizer organizerSelected;
	// Objeto asociado al search
	private Organizer organizerSearch;

	private Action action;
	// Style for panelGrid and dataTable
	private String stylePanelOrganizer;
	private String styleTableOrganizer;

	// Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	// Text in Confirm Dialog
	private String messageConfirmDialog;

	@Inject
	private OrganizerService organizerService;

	@PostConstruct
	public void init() {
		this.organizerSearch = new Organizer();
		this.cleanForm();
		this.loadOrganizers();
		this.action = Action.NONE;
		this.stateList();
	}

	public void loadOrganizers() {
		try {
			this.organizers = organizerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cleanForm() {
		this.organizer = new Organizer();
		this.organizerSelected = null;
	}

	public void newOrganizer() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Creando nuevo Organizador");
	}

	public void saveOrganizer() {
		boolean uniqueNumeroDocumento = true;
		if (this.action == Action.NEW || this.action == Action.EDIT) {
			try {
				// Para verificar que el número de documento es único ESTA PARTE PUEDE HABER
				// ERROR
				Optional<Organizer> optional = organizerService.findBydniOrganizer(organizer.getDni());
				if (optional.isPresent()) {
					if (!optional.get().getId().equals(organizer.getId())) {
						uniqueNumeroDocumento = false;
					}
				}
				if (uniqueNumeroDocumento == true) {
					if (this.action == Action.NEW) {
						organizerService.save(this.organizer);
					} else
						organizerService.update(this.organizer);
					cleanForm();
					loadOrganizers();
					this.action = Action.NONE;
					this.stateList();
				} else { // ESTA PARTE PUEDE HABER ERROR
					this.addMessage("El número de documento: " + organizer.getDni() + " ya existe");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void cancelOrganizer() {
		cleanForm();
		this.stateList();
	}

	public void selectOrganizer(SelectEvent<Organizer> e) {
		this.organizerSelected = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.organizerSelected.getNameOrganizer();
		this.stateSelectRow();
	}

	public void unselectOrganizer(UnselectEvent<Organizer> e) {
		this.organizerSelected = null;
		this.stateList();
	}

	public void editOrganizer() {
		if (this.organizerSelected != null) {
			this.organizer = this.organizerSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteOrganizer() {
		if (this.organizerSelected != null) {
			try {
				organizerService.deleteById(this.organizerSelected.getId());
				cleanForm();
				loadOrganizers();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void findByNameOrganizer() { // CAMBIO POSIBLE ERROR
		if (!this.organizerSearch.getNameOrganizer().isEmpty()) {
			try { // CAMBIO POSIBLE ERROR
				this.organizers = organizerService.findByNameOrganizer(organizerSearch.getNameOrganizer());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void findBySurnameOrganizer() { // CAMBIO POSIBLE ERROR
		if (!this.organizerSearch.getSurnameOrganizer().isEmpty()) {
			try { // CAMBIO POSIBLE ERROR
				this.organizers = organizerService.findBySurnameOrganizer(organizerSearch.getSurnameOrganizer());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void findBydni() { // CAMBIO POSIBLE ERROR
		if (!this.organizerSearch.getDni().isEmpty()) {
			try {
				this.organizers = new ArrayList<>(); // POSIBLE ERROR
				Optional<Organizer> optional = organizerService.findBydniOrganizer(organizerSearch.getDni());
				if (optional.isPresent()) {
					this.organizers.add(optional.get());
				}
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void cleanByNombres() { // GETPERSONA? O SETPERSONA?
		this.organizerSearch.setNameOrganizer("");
		loadOrganizers();
		this.stateList();
	}

	public void cleanByApellidos() { // GETPERSONA? O SETPERSONA?
		this.organizerSearch.setSurnameOrganizer("");
		loadOrganizers();
		this.stateList();
	}

	public void cleanBydni() { // GETPERSONA? O SETPERSONA?
		this.organizerSearch.setDni("");
		loadOrganizers();
		this.stateList();
	}

	// State on Application
	public void stateList() {
		this.stylePanelOrganizer = "display:none;";
		this.styleTableOrganizer = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelOrganizer = "display:block;";
		this.styleTableOrganizer = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelOrganizer = "display:none;";
		this.styleTableOrganizer = "display:block;";
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

	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public OrganizerService getOrganizerService() {
		return organizerService;
	}
	
	public Organizer getOrganizer() {
		return organizer;
	}

	public String getStyleTableOrganizer() {
		return styleTableOrganizer;
	}

	public String getStylePanelOrganizer() {
		return stylePanelOrganizer;
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

	public Organizer getOrganizerSearch() {
		return organizerSearch;
	}

}
