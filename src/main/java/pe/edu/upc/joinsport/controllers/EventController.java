package pe.edu.upc.joinsport.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.criterion.Distinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.entities.Event;
import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.entities.Sport;
import pe.edu.upc.joinsport.models.entities.TypeEvent;
import pe.edu.upc.joinsport.models.entities.TypePay;
import pe.edu.upc.joinsport.security.UserDetails;
import pe.edu.upc.joinsport.services.DistrictService;
import pe.edu.upc.joinsport.services.EventService;
import pe.edu.upc.joinsport.services.OrganizerService;
import pe.edu.upc.joinsport.services.PrimeryService;
import pe.edu.upc.joinsport.services.SportService;
import pe.edu.upc.joinsport.services.TypeEventService;
import pe.edu.upc.joinsport.services.TypePayService;

@Controller
@RequestMapping("/events") // GET y POST
@SessionAttributes("event")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private SportService sportService;

	@Autowired
	private TypeEventService typeEventService;

	@Autowired
	private TypePayService typePayService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private OrganizerService organizerService;
	
	@Autowired
	private PrimeryService primaryUserServices;

	// METODO PARA PODER ENCONTRAR LOS EVENTOS EN LA PAGINA PRINCIPAL
	@GetMapping
	public String listEvent(Model model ) {
		try {
			
			List<District> districts = districtService.findAll();
			model.addAttribute("districts", districts);
			
			List<Sport> sports = sportService.findAll();
			model.addAttribute("sports", sports);
			
			List<Event> events = eventService.findAll();
			model.addAttribute("events", events);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/list";
	}
	
	//METODO PARA FILTRAR LA BUSQUEDA POR DEPORTE
	@GetMapping("/searchSport/{nameSport}")
	public String searchSport(@RequestParam("nameSport") String nameSport, Model model) {
		try {
			List<Event> searchEvents = eventService.findByNameSport(nameSport);
			System.out.println(nameSport); // --> para verificar en la consola si realmente me está buscado el nombre del deporte
			if (nameSport.isEmpty()) {
				return "redirect:/events";
			} else {
				model.addAttribute("searchEvents", searchEvents);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/searchSport";
	}
	
	//METODO PARA FILTRAR LA BUSQUEDA POR DISTRITO
	@GetMapping("/searchDistrict/{nameDistrict}")
	public String searchDistrict(@RequestParam("nameDistrict") String nameDistrict, Model model) {
		try {
			List<Event> searchEvents = eventService.findByNameDistrict(nameDistrict);
			System.out.println(nameDistrict); // --> para verificar en la consola si realmente me está buscado el nombre del districto
			if (nameDistrict.isEmpty()) {
				return "redirect:/events";
			} else {
				model.addAttribute("searchEvents", searchEvents);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/searchDistrict";
	}

	// METODO PARA CREAR UN NUEVO EVENTO
	@GetMapping("new")
	public String newEvent(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
		Primaryuser primaryUser = new Primaryuser();
		Event event = new Event();
		try {
			
			Optional<Primaryuser> primaryUserLogin = primaryUserServices.findByUsername(primaryuserDetail.getUsername());
			model.addAttribute("primaryUserLogin", primaryUserLogin.get());
			model.addAttribute("primaryUser", primaryUser);
			// CARGAR DATOS EN LOS COMBOS
			List<Event> events = eventService.findAll();
			List<Sport> sports = sportService.findAll();
			List<TypeEvent> typeEvents = typeEventService.findAll();
			List<TypePay> typepays = typePayService.findAll();
			List<District> districts = districtService.findAll();
			
			model.addAttribute("events", events);
			model.addAttribute("sports", sports);
			model.addAttribute("typeEvents", typeEvents);
			model.addAttribute("typepays", typepays);
			model.addAttribute("districts", districts);
			model.addAttribute("event", event);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "events/new";
	}

	// METODO PARA GUARDAR UN EVENTO *//CORREGIR 
	@PostMapping("saveNew") // POST: /event/saveNew
	public String saveNew(Model model, @ModelAttribute("event") Event event, SessionStatus status) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
		Primaryuser primaryUser = new Primaryuser();
		try {
			
			//CREATE PRIMARYUSER
			Random r = new Random();
			Integer aleatorio = r.nextInt(2);
			Organizer organizer = new Organizer();
			organizer.setId(aleatorio);
			event.setOrganizer(organizer);
			
			
			eventService.save(event);
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/events";
	}

	// METODO PARA BUSCAR EVENTOS POR NOMBRE
	@GetMapping("/search/{nameEvent}")
	public String searchEvent(@RequestParam("nameEvent") String nameEvent, Model model) {
		// model.addAttribute("eventSearch", eventSearch);

		try {
			List<Event> searchEvents = eventService.findByNameEvent(nameEvent);
			// System.out.println(nameEvent); --> para verificar en la consola si realmente me está buscado el nombre
			if (nameEvent.isEmpty()) {
				return "redirect:/events";
			} else {
				model.addAttribute("searchEvents", searchEvents);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/searchEvent";
	}

	// METODO PARA VER EL DETALLE DEL EVENTO(*vista para un organizador y un participante*)
	@GetMapping("view/{id}")
	public String viewEvent(@PathVariable("id") Integer id, Model model) {

		try {
			Optional<Event> optional = eventService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("event", optional.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/viewEvent";
	}

	// METODO PARA RELACIONAR EN LA VISTA DEL EVENTO LOS EVENTOS SIMILARES (FALTA IMPLEMENTAR)

	// METODO PARA VER LOS CAMPOS DEL EVENTO AL MOMENTO DE EDITAR
	@GetMapping("editViewEvent/{id}")
	public String editEvent(@PathVariable("id") Integer id, Model model) {

		try {
			Optional<Event> optional = eventService.findById(id);
			System.out.println(optional);
			if (optional.isPresent()) {
				List<Event> events = eventService.findAll();
				List<Sport> sports = sportService.findAll();
				List<TypeEvent> typeEvents = typeEventService.findAll();
				List<TypePay> typepays = typePayService.findAll();
				List<District> districts = districtService.findAll();
				List<Organizer> organizers = organizerService.findAll();
				model.addAttribute("events", events);
				model.addAttribute("sports", sports);
				model.addAttribute("typeEvents", typeEvents);
				model.addAttribute("typepays", typepays);
				model.addAttribute("districts", districts);
				model.addAttribute("organizers", organizers);
				model.addAttribute("event", optional.get());
				System.out.println(optional.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "events/editViewEvent";
	}

	// METODO PARA ACTUALIZAR EL EVENTO (EDIT EVENT)
	@PostMapping("update/{id}")
	public String updateEvent( @PathVariable("id") Integer id, @ModelAttribute("event") Event event, Model model,
			SessionStatus status, RedirectAttributes attributes) {

		try {
			Optional<Event> getEvent = eventService.findById(id);
			if(getEvent.isPresent()) {
				eventService.update(event);
				status.setComplete();
				attributes.addFlashAttribute("mensaje", "Se actualizó correctamente el evento");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/events/";
	}

	// METODO PARA ELIMINAR UN EVENTO

}
