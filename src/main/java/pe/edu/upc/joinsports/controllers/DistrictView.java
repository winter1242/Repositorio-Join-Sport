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
import pe.edu.upc.joinsports.services.DistrictService;
import pe.edu.upc.joinsports.util.Action;

@Named("districtView")
@ViewScoped
public class DistrictView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<District> districts;
	private District district;

	private District selectDistrict;

	private Action action;

	// Style for panelGrid and dataTable
	private String stylePanelDistrict;
	private String styleTableDistrict;

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
	private DistrictService districtService;

	@PostConstruct
	public void init() {
		cleanForm();
		loadDistricts();
		action = Action.NONE;
	}

	public void loadDistricts() {
		try {
			this.districts = districtService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Método que se ejecuta cuando hago click en 'Nuevo'
	public void newDistrict() {
		action = Action.NEW;
		cleanForm();
		this.stateNewEdit();
		addMessage("Creando nuevo Distrito");
	}

	public void cleanForm() {
		this.district = new District();
		this.selectDistrict = null;
	}

	// Metodo para grabar el elemento ingresado en el formulario
	public void saveDistrict() {
		try {
			if (action == Action.NEW) {
				districtService.save(this.district);
				addMessage("Se grabo de forma correcta el nuevo distrito");
			} else if (action == Action.EDIT) {
				districtService.update(this.district);
				addMessage("Se actualizo de forma correcta el distrito");
			}
			cleanForm();
			loadDistricts();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void cancelDistrict() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cada vez que el usuario selecciona una fila del
	// datatable
	public void selectDistrict(SelectEvent<District> e) {
		this.selectDistrict = e.getObject(); // PUEDE HABER ERROR EN ESTA PARTE
		this.messageConfirmDialog = this.selectDistrict.getNameDistrict();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cada vez que el usuario des-selecciona una fila del
	// datatable
	public void unselectDistrict(UnselectEvent<District> e) {
		this.selectDistrict = null;
		this.stateList();
	}

	public void editDistrict() {
		if (this.selectDistrict != null) {
			this.district = this.selectDistrict;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteDistrict() {
		if (this.selectDistrict != null) {
			try {
				districtService.deleteById(this.selectDistrict.getId());
				cleanForm();
				loadDistricts();
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
		this.stylePanelDistrict = "display:none;";
		this.styleTableDistrict = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelDistrict = "display:block;";
		this.styleTableDistrict = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelDistrict = "display:none;";
		this.styleTableDistrict = "display:block;";
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

	public List<District> getDistricts() {
		return districts;
	}
	
	public DistrictService getDistrictService() {
		return districtService;
	}

	public District getDistrict() {
		return district;
	}
	
	public String getStyleTableDistrict() {
		return styleTableDistrict;
	}

	public String getStylePanelDistrict() {
		return stylePanelDistrict;
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
