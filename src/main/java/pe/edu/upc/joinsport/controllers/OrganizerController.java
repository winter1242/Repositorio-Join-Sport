package pe.edu.upc.joinsport.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.text.Segment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.entities.Event;
import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.entities.Sport;
import pe.edu.upc.joinsport.security.UserDetails;
import pe.edu.upc.joinsport.services.DistrictService;
import pe.edu.upc.joinsport.services.EventService;
import pe.edu.upc.joinsport.services.EventUserService;
import pe.edu.upc.joinsport.services.OrganizerService;
import pe.edu.upc.joinsport.services.PrimeryService;
import pe.edu.upc.joinsport.services.SportService;
import pe.edu.upc.joinsport.utils.Segmento;

@Controller
@RequestMapping("/organizers") // GET y POST
@SessionAttributes("organizer")
public class OrganizerController {
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private OrganizerService organizerService;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventUserService eventUserService;
	
	@Autowired
	private PrimeryService primaryService;

	// METODO PARA VER MI PERFIL //ADAPTARLO LUEGO A CADA ORGANIZADOR!!
	@GetMapping("/myProfile")//{/id} --> este de aqui me muestra los datos bloqueados
	public String myProfile( /*@PathVariable("id") Integer id,*/  Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
		try {
			if (primaryuserDetail.getSegmento() == Segmento.ORGANIZER) {
				 Optional<Organizer> organizer = organizerService.findById(primaryuserDetail.getIdSegmento());
				 //Optional<Primaryuser> primaryuser = primaryService.findById(organizer.get().getPrimaryuser().getId()); // cambiar
				 System.out.println(organizer);
				 //System.out.println(primaryuser);
				 if (organizer.isPresent()) {
				//List<Organizer> organizers = organizerService.findAll();
				//model.addAttribute("organizers", organizers);
					 model.addAttribute("organizer", organizer.get());
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "organizers/myProfile";
	}

	// METODO PARA PODER VER MI PERFIL --> habilitacion de las cajas de textos
	@GetMapping("/viewMyProfile/{id}")//{id} --> este de aqui me muestra los datos desbloqueados
	public String viewMyProfile( @PathVariable("id") Integer id, Model model) {
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
			//System.out.println(primaryuserDetail.getSegmento());
			//Organizer id = new Organizer();
			//if (primaryuserDetail.getSegmento() == Segmento.ORGANIZER) {
				 
				 Optional<Organizer> organizer = organizerService.findById(id);
				 //Optional<Primaryuser> primaryuser = primaryService.findById(organizer.get().getPrimaryuser().getId()); // cambiar
				 System.out.println(organizer);
				 //System.out.println(primaryuser);
				 if (organizer.isPresent()) {
				//List<Organizer> organizers = organizerService.findAll();
				//model.addAttribute("organizers", organizers);
				model.addAttribute("organizer", organizer.get());
				 }
			//}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "organizers/editProfile";
	}

	// METODO PARA ACTUALIZAR MI PERFIL (EDIT PROFILE) --> implementar para el final
	// (validacion de organizador *security*)
	@PostMapping("/updateOrganizer/{id}") // '/{id}' --> AGREGAR LUEGO CUANDO SE IMPLEMENTE EL SECURITY
	public String updateOrganizer(
			 @PathVariable("id") Integer id,  @ModelAttribute("organizer") Organizer organizer, Model model,
			SessionStatus status, RedirectAttributes attributes) {

		try {
			Optional<Organizer> getOrganizer = organizerService.findById(id);
			if(getOrganizer.isPresent()) {
				organizerService.update(organizer);
				status.setComplete();
				attributes.addFlashAttribute("mensaje", "Se actualizó correctamente el usuario");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/organizers/editProfile";
	}

	// METODO PARA ENCONTRAR LOS EVENTOS CREADOS DE CADA ORGANIZADOR //ADAPTARLO
	// LUEGO A CADA ORGANIZADOR!!!
	// LUEGO SE ADAPTA A CADA ORGANIZADOR AL MOMENTO DE REALIZAR EL SPRING
	@GetMapping("/viewEventOrganizer")//{id}
	public String viewEventOrganizer(/* @PathVariable("id") Integer id, */ Model model) {
		/*
		try {
			Optional<Organizer> organizer = organizerService.findById(id);
			if (organizer.isPresent()) {
				List<District> districts = districtService.findAll();
				model.addAttribute("districts", districts);

				List<Sport> sports = sportService.findAll();
				model.addAttribute("sports", sports);
			
				List<Event> events = eventService.findAll();
				model.addAttribute("events", events);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		*/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
		try {
			if (primaryuserDetail.getSegmento() == Segmento.ORGANIZER) {
				 Optional<Organizer> organizer = organizerService.findById(primaryuserDetail.getIdSegmento());
				 //Optional<Primaryuser> primaryuser = primaryService.findById(organizer.get().getPrimaryuser().getId()); // cambiar
				 System.out.println(organizer);
				 //System.out.println(primaryuser);
				 if (organizer.isPresent()) {
				//List<Organizer> organizers = organizerService.findAll();
				//model.addAttribute("organizers", organizers);
					 model.addAttribute("organizer", organizer.get());
					 List<District> districts = districtService.findAll();
						model.addAttribute("districts", districts);

						List<Sport> sports = sportService.findAll();
						model.addAttribute("sports", sports);
					
						List<Event> events = eventService.findAll();
						model.addAttribute("events", events);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return "organizers/myEvents";
	}
	
	@GetMapping("/registerOrganizer")
	private String registerOrganizer (Model model) {
		Organizer organizer = new Organizer();
		Primaryuser primaryUser = new Primaryuser();
		try {
			model.addAttribute("organizer", organizer);
			model.addAttribute("primaryUser", primaryUser);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "organizers/register";
	}
	
	@PostMapping("/registerOrganizer")
	private String saveOrganizer (Model model, @ModelAttribute("organizer") Organizer organizer, SessionStatus status) {
		Primaryuser primaryUser = new Primaryuser();
		//UserDetails primaryuserDetail = new UserDetails(primaryUser);
		try {
			//CREAR PRIMARYUSER /*
			primaryUser.setEnable(true);
			primaryUser.setIdSegmento(9);
			primaryUser.setUsername(organizer.getUserOrganizer());
			
			// Contraseña
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			String password = bcpe.encode(organizer.getPasswordOrganizer().toString());
			primaryUser.setPassword(password);
			
			//SEGMENTO
			primaryUser.setSegmento(primaryUser.getSegmento().ORGANIZER);
			
			// Authority
			primaryUser.addAuthority("ROLE_ORGANIZER");
			//System.out.println(primaryUser.getId());
			
			primaryService.save(primaryUser);
			organizerService.save(organizer);
			
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/login";
	}
}
