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

import pe.edu.upc.joinsports.models.entities.District;
import pe.edu.upc.joinsports.models.entities.Event;
import pe.edu.upc.joinsports.models.entities.Organizer;
import pe.edu.upc.joinsports.models.entities.Sport;
import pe.edu.upc.joinsports.models.entities.TypeEvent;
import pe.edu.upc.joinsports.models.entities.TypePay;
import pe.edu.upc.joinsports.services.DistrictService;
import pe.edu.upc.joinsports.services.EventService;
import pe.edu.upc.joinsports.services.OrganizerService;
import pe.edu.upc.joinsports.services.SportService;
import pe.edu.upc.joinsports.services.TypeEventService;
import pe.edu.upc.joinsports.services.TypePayService;
import pe.edu.upc.joinsports.util.Action;

@Named("eventView")
@ViewScoped
public class EventView implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Event> events;

	private Event event;
	private Event eventSelected;
	private Event eventSearch;

	private List<District> districts;
	private District districtSelected;

	private List<Sport> sports;
	private Sport sportSelected;

	private List<TypeEvent> typeEvents;
	private TypeEvent typeEventSelected;

	private List<TypePay> typePays;
	private TypePay typePaySelected;

	private List<Organizer> organizers;
	private Organizer organizerSelected;

	private Action action;

	private String stylePanelEvent;
	private String styleTableEvent;

	// Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	// Text in Confirm Dialog
	private String messageConfirmDialog;

	@Inject
	private EventService eventService;

	@Inject
	private DistrictService districtService;

	@Inject
	private SportService sportService;

	@Inject
	private TypeEventService typeEventService;

	@Inject
	private TypePayService typePayService;

	@Inject
	private OrganizerService organizerService;

	@PostConstruct
	public void init() {
		this.eventSearch = new Event();
		this.cleanForm();
		this.loadEvents();
		this.loadDistricts();
		this.loadSports();
		this.loadTypeEvents();
		this.loadTypePays();
		this.loadOrganizer();
		this.action = Action.NONE;
		this.stateList();
	}

	public void loadEvents() {
		try {
			this.events = eventService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void loadOrganizer(Integer id) {
		try {
			this.organizerSelected = (organizerService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadDistrict(Integer id) {
		try {
			this.districtSelected = (districtService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadSport(Integer id) {
		try {
			this.sportSelected = (sportService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadTypeEvent(Integer id) {
		try {
			this.typeEventSelected = (typeEventService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadTypePay(Integer id) {
		try {
			this.typePaySelected = (typePayService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadDistricts() {
		try {
			this.districts = districtService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadSports() {
		try {
			this.sports = sportService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadTypeEvents() {
		try {
			this.typeEvents = typeEventService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadTypePays() {
		try {
			this.typePays = typePayService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadOrganizer() {
		try {
			this.organizers = organizerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void cleanForm() {
		this.event = new Event();
		this.eventSelected = null;
	}

	// Metodo cuando se hace click en el boton Nuevo
	public void newEvent() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Creando nuevo Evento");
	}

	// Funciona cuando se cambia el distrito
	public void changeDistrict() {
		if (this.event.getDistrict() != null) {
			if (!this.event.getDistrict().getId().equals(this.event.getDistrictId())) {
				loadDistrict(this.event.getDistrictId());
				this.event.setDistrict(this.districtSelected);
			}
		} // Cuando es un nuevo User
		else {
			loadDistrict(this.event.getDistrictId());
			this.event.setDistrict(this.districtSelected);
		}
	}

	// AGREGAR MAS DATOS DEL CODIGO

	// Funciona cuando se cambia el sport
	public void changeSport() {
		if (this.event.getSport() != null) {
			if (!this.event.getSport().getId().equals(this.event.getSportId())) {
				loadSport(this.event.getSportId());
				this.event.setSport(this.sportSelected);
			}
		} // Cuando es un nuevo User
		else {
			loadSport(this.event.getSportId());
			this.event.setSport(this.sportSelected);
		}
	}

	// Funciona cuando se cambia el TypeEvent
	public void changeTypeEvent() {
		if (this.event.getTypeEvent() != null) {
			if (!this.event.getTypeEvent().getId().equals(this.event.getTypeEventId())) {
				loadTypeEvent(this.event.getTypeEventId());
				this.event.setTypeEvent(this.typeEventSelected);
			}
		} // Cuando es un nuevo User
		else {
			loadTypeEvent(this.event.getTypeEventId());
			this.event.setTypeEvent(this.typeEventSelected);
		}
	}

	// Funciona cuando se cambia el TypePay
	public void changeTypePay() {
		if (this.event.getTypepay() != null) {
			if (!this.event.getTypepay().getId().equals(this.event.getTypePayId())) {
				loadTypePay(this.event.getTypePayId());
				this.event.setTypepay(this.typePaySelected);
			}
		} // Cuando es un nuevo User
		else {
			loadTypePay(this.event.getTypePayId());
			this.event.setTypepay(this.typePaySelected);
		}
	}

	// Funciona cuando se cambia el Organizer
	public void changeOrganizer() {
		if (this.event.getOrganizer() != null) {
			if (!this.event.getOrganizer().getId().equals(this.event.getOrganizerId())) {
				loadOrganizer(this.event.getOrganizerId());
				this.event.setOrganizer(this.organizerSelected);
			}
		} // Cuando es un nuevo Organizador
		else {
			loadOrganizer(this.event.getOrganizerId());
			this.event.setOrganizer(this.organizerSelected);
		}
	}

	// Metodo para grabar el elemento ingresado en el formulario
	public void saveEvent() {
		try {
			if (action == Action.NEW) {
				eventService.save(this.event);
				addMessage("Se grabo de forma correcta el nuevo evento");
			} else if (action == Action.EDIT) {
				eventService.update(this.event);
				addMessage("Se actualizo de forma correcta el evento");
			}
			cleanForm();
			loadEvents();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cancelEvent() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectEvent(SelectEvent<Event> e) {
		this.eventSelected = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.eventSelected.getNameEvent();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cuando el evento rowUnselect ocurra
	public void unselectEvent(UnselectEvent<Event> e) {
		this.eventSelected = null;
		this.stateList();
	}

	// Método que se ejecuta cuando hago click en el boton Editar
	public void editEvent() {
		if (this.eventSelected != null) {
			this.event = this.eventSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	// Método que se ejecuta cuando hago click en el boton Eliminar
	public void deleteEvent() {
		if (this.eventSelected != null) {
			try {
				eventService.deleteById(this.eventSelected.getId());
				cleanForm();
				loadEvents();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	/*FALTA METODO PARA ENCONTRAR POR FECHAS IMPLEMENTACION LUEGO*/
	/*FALTA METODO PARA LIMPIAR LAS FECHAS INGRESADAS PARA BUSCAR POR FECHAS*/
	// -----------------------------

	// State on Application
	public void stateList() {
		this.stylePanelEvent = "display:none;";
		this.styleTableEvent = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelEvent = "display:block;";
		this.styleTableEvent = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelEvent = "display:none;";
		this.styleTableEvent = "display:block;";
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
	
	public EventService getEventServices() {
		return eventService;
	}

	public List<Event> getEvents() {
		return events;
	}

	public Event getEvent() {
		return event;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}
	
	public List<Sport> getSports() {
		return sports;
	}

	public SportService getSportService() {
		return sportService;
	}
	
	public List<TypeEvent> getTypeEvents() {
		return typeEvents;
	}

	public TypeEventService getEventService() {
		return typeEventService;
	}
	
	public List<TypePay> getTypePays() {
		return typePays;
	}

	public TypePayService getTypePayService() {
		return typePayService;
	}
	
	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public OrganizerService getOrganizerService() {
		return organizerService;
	}
	
	public String getStyleTableEvent() {
		return styleTableEvent;
	}

	public String getStylePanelEvent() {
		return stylePanelEvent;
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
	
	public Event getEventSearch() {
		return eventSearch;
	}
	
}
