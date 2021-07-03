package pe.edu.upc.joinsport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.entities.Event;
import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.entities.Sport;
import pe.edu.upc.joinsport.models.entities.User;
import pe.edu.upc.joinsport.models.repositories.DistrictRepository;
import pe.edu.upc.joinsport.models.repositories.UserRepository;
import pe.edu.upc.joinsport.services.DistrictService;
import pe.edu.upc.joinsport.services.EventService;
import pe.edu.upc.joinsport.services.EventUserService;
import pe.edu.upc.joinsport.services.SportService;
import pe.edu.upc.joinsport.services.UserService;

@Controller
@RequestMapping("/competitors") // GET y POST
@SessionAttributes("competitor")
public class CompetitorController {
	@Autowired
	private UserService userService;

	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private EventUserService eventUserService;
	
	@Autowired
	private EventService eventService;

	// METODO PARA VER MI PERFIL //ADAPTARLO LUEGO A CADA PARTICIPANTE!!
	@GetMapping("/myProfile")
	public String myProfile(/* @PathVariable("id") Integer id, */ Model model) {
		try {
			// Optional<User> optional = userService.findById(id);
			// System.out.println(optional);
			// if (optional.isPresent()) {
			List<District> districts = districtService.findAll();
			model.addAttribute("districts", districts);

			// model.addAttribute("organizer", optional.get());

			// }

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "competitors/myProfile";
	}

	// METODO PARA PODER VER MI PERFIL --> habilitacion de las cajas de textos
	@GetMapping("/viewMyProfile")
	public String viewMyProfile(/* @PathVariable("id") Integer id, */ Model model) {
		try {
			// Optional<User> optional = userService.findById(id);
			// System.out.println(optional);
			// if (optional.isPresent()) {
			List<District> districts = districtService.findAll();
			model.addAttribute("districts", districts);

			// model.addAttribute("organizer", optional.get());

			// }

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "competitors/editProfile";
	}

	// METODO PARA ACTUALIZAR MI PERFIL (EDIT PROFILE) --> implementar para el final
	// (validacion de participante *security*)
	@PostMapping("/updateCompetitor") // '/{id}' --> AGREGAR LUEGO CUANDO SE IMPLEMENTE EL SECURITY
	public String updateOrganizer(/* @PathVariable("id") Integer id, */ @ModelAttribute("user") User user, Model model,
			SessionStatus status, RedirectAttributes attributes) {

		try {
			// Optional<User> getUser = userService.findById(id);
			// if(getEvent.isPresent()) {
			userService.update(user);
			status.setComplete();
			attributes.addFlashAttribute("mensaje", "Se actualizó correctamente el usuario");
			// }

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/competitors/editProfile";
	}

	// METODO PARA ENCONTRAR LOS EVENTOS CREADOS DE CADA COMPETIDOR //ADAPTARLO
	// LUEGO A CADA COMPETIDOR!!!
	// LUEGO SE ADAPTA A CADA COMPETIDOR AL MOMENTO DE REALIZAR EL SPRING SECURITY
	@GetMapping("viewEventCompetitor")
	public String viewEventCompetitor(/* @PathVariable("id") Integer id, */ Model model) {

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

		return "competitors/myEvents";
	}

}
