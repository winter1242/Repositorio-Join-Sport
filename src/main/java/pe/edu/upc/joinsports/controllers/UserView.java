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

import pe.edu.upc.joinsports.models.entities.District;
import pe.edu.upc.joinsports.models.entities.User;
import pe.edu.upc.joinsports.services.DistrictService;
import pe.edu.upc.joinsports.services.UserService;
import pe.edu.upc.joinsports.util.Action;

@Named("userView")
@ViewScoped
public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users;

	// Objeto asociado al formulario del usuario
	private User user;
	// Objeto asociado al rowSelect del datatable
	private User userSelected;
	// Objeto asociado al search
	private User userSearch;

	private List<District> districts;
	private District districtSelected;

	private Action action;
	// Estilo para panelGrid y dataTable
	private String stylePanelUser;
	private String styleTableUser;

	// Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	// Text in Confirm Dialog
	private String messageConfirmDialog;

	@Inject
	private UserService userService;

	@Inject
	private DistrictService districtService;

	@PostConstruct
	public void init() {
		this.userSearch = new User();
		this.cleanForm();
		this.loadUsers();
		this.loadDistricts();
		this.action = Action.NONE;
		this.stateList();
	}

	public void loadUsers() {
		try {
			this.users = userService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
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

	public void loadDistricts() {
		try {
			this.districts = districtService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void cleanForm() {
		this.user = new User();
		this.userSelected = null;
	}

	// Metodo cuando se hace click en el boton Nuevo
	public void newUser() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Creando nuevo Usuario");
	}

	// Funciona cuando se cambia el distrito
	public void changeDistrict() {
		if (this.user.getDistrict() != null) {
			if (!this.user.getDistrict().getId().equals(this.user.getDistrictId())) {
				loadDistrict(this.user.getDistrictId());
				this.user.setDistrict(this.districtSelected);
			}
		} // Cuando es un nuevo User
		else {
			loadDistrict(this.user.getDistrictId());
			this.user.setDistrict(this.districtSelected);
		}
	}

	// Método cuando se hace click en el boton Grabar
	public void saveUser() {
		boolean uniqueNumeroDocumento = true;
		if (this.action == Action.NEW || this.action == Action.EDIT) {
			try {
				// Para verificar que el número de documento es único
				Optional<User> optional = userService.findBydniUser(user.getDniUser());
				if (optional.isPresent()) {
					if (!optional.get().getId().equals(user.getId())) {
						uniqueNumeroDocumento = false;
					}
				}
				if (uniqueNumeroDocumento == true) {
					changeDistrict();
					if (this.action == Action.NEW) {
						userService.save(this.user);
					} else
						userService.update(this.user);
					cleanForm();
					loadUsers();
					this.action = Action.NONE;
					this.stateList();
				} else { // ESTA PARTE PUEDE HABER ERROR
					this.addMessage("El número de documento: " + user.getDniUser() + " ya existe");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// Método cuando se hace click en el boton Cancelar
	public void cancelUser() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectUser(SelectEvent<User> e) {
		this.userSelected = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.userSelected.getNameUser();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cuando el evento rowUnselect ocurra
	public void unselectUser(UnselectEvent<User> e) {
		this.userSelected = null;
		this.stateList();
	}

	// Método que se ejecuta cuando hago click en el boton Editar
	public void editUser() {
		if (this.userSelected != null) {
			this.user = this.userSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	// Método que se ejecuta cuando hago click en el boton Eliminar
	public void deleteUser() {
		if (this.userSelected != null) {
			try {
				userService.deleteById(this.userSelected.getId());
				cleanForm();
				loadUsers();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// Metodo para encontrar al arrendador por Nombre
	public void findByNameUser() { // CAMBIO POSIBLE ERROR
		if (!this.userSearch.getNameUser().isEmpty()) {
			try { // CAMBIO POSIBLE ERROR
				this.users = userService.findByNameUser(userSearch.getNameUser());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// Metodo para encontrar al arrendador por Apellido
	public void findBySurnameUser() { // CAMBIO POSIBLE ERROR
		if (!this.userSearch.getSurnameUser().isEmpty()) {
			try { // CAMBIO POSIBLE ERROR
				this.users = userService.findBySurnameUser(userSearch.getSurnameUser());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// Metodo para encontrar al user por DNI
	public void findBydni() { // CAMBIO POSIBLE ERROR
		if (!this.userSearch.getDniUser().isEmpty()) {
			try {
				this.users = new ArrayList<>(); // POSIBLE ERROR
				Optional<User> optional = userService.findBydniUser(userSearch.getDniUser());
				if (optional.isPresent()) {
					this.users.add(optional.get());
				}
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// LIMPIAR NOMBRES USERS
	public void cleanByNombres() { // GETPERSONA? O SETPERSONA?
		this.userSearch.setNameUser("");
		loadUsers();
		this.stateList();
	}

	// LIMPIAR APELLIDOS USERS
	public void cleanByApellidos() { // GETPERSONA? O SETPERSONA?
		this.userSearch.setSurnameUser("");
		loadUsers();
		this.stateList();
	}

	// LIMPIAR DNI USERS
	public void cleanBydni() { // GETPERSONA? O SETPERSONA?
		this.userSearch.setDniUser("");
		loadUsers();
		this.stateList();
	}

	// State on Application
	public void stateList() {
		this.stylePanelUser = "display:none;";
		this.styleTableUser = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelUser = "display:block;";
		this.styleTableUser = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelUser = "display:none;";
		this.styleTableUser = "display:block;";
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

	public UserService getUserServices() {
		return userService;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public String getStyleTableUser() {
		return styleTableUser;
	}

	public String getStylePanelUser() {
		return stylePanelUser;
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

	public User getUserSearch() {
		return userSearch;
	}

}
