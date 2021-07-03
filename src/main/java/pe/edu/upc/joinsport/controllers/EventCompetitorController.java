package pe.edu.upc.joinsport.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.entities.Event;
import pe.edu.upc.joinsport.models.entities.EventUser;
import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.entities.Sport;
import pe.edu.upc.joinsport.models.entities.User;
import pe.edu.upc.joinsport.security.UserDetails;
import pe.edu.upc.joinsport.services.EventService;
import pe.edu.upc.joinsport.services.EventUserService;
import pe.edu.upc.joinsport.services.PrimeryService;
import pe.edu.upc.joinsport.services.UserService;
import pe.edu.upc.joinsport.utils.Segmento;

@Controller
@RequestMapping("/eventCompetitors") // GET y POST
@SessionAttributes("eventCompetitor")
public class EventCompetitorController {
	@Autowired
	private UserService competitorService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private PrimeryService primaryuserServices;
	
	@Autowired
	private EventUserService eventCompetitorService;
	
	
	//METODO PARA PODER VER LOS DATOS CARGADOS EN EL HTML
	@GetMapping("ec/{id}")
	public String eventCompetitor(@PathVariable("id") Integer id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
        Primaryuser primaryUser = new Primaryuser();
        Event event = new Event();
		//EventUser eventUser = new EventUser();
		try {/*
			if (primaryuserDetail.getSegmento() == Segmento.COMPETITOR) {
				Optional<User> competitor = competitorService.findById(primaryuserDetail.getIdSegmento());
				model.addAttribute("competitor", competitor.get());
			
				if (competitor.isPresent()) {
					List<Event> events = eventService.findAll();
					model.addAttribute("events", events);
			
					model.addAttribute("eventUser",eventUser);
				}
			}*/
			Optional<Primaryuser> primaryUserLogin = primaryuserServices.findByUsername(primaryuserDetail.getUsername());
			model.addAttribute("primaryUserLogin", primaryUserLogin.get());
			model.addAttribute("primaryUser", primaryUser);
			
			Optional<Event> idEvent = eventService.findById(id);
			model.addAttribute("event", idEvent.get());
			model.addAttribute("events", event);
			//model.addAttribute("user", user.get());
			//List<Event> events = eventService.findAll();
			//model.addAttribute("events", events);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "event-competitor/new";
	}
	
	//METODO PARA CREAR A UN COMPETIDOR A UN EVENTO --> TODAVIA NO SE UTILIZA PERO SE DEJA IMPLEMENTADO PARA REALIZARLO EN EL FINAL
	@GetMapping("event-competitor/{id}")
	public String eventCompetitorNew (@PathVariable("id") Integer id, /*@ModelAttribute("eventUser") EventUser eventUser, SessionStatus status*/ Model model) {
		EventUser eventUser = new EventUser();
		try {
			Optional<Event> event = eventService.findById(id);
			//System.out.println(id);
			if (event.isPresent()) {
				//eventCompetitorService.save(eventUser);
				//status.setComplete();
				List<User> users = competitorService.findAll();
				model.addAttribute("users", users);
				model.addAttribute("event", event.get());
				model.addAttribute("eventUser",eventUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "/event-competitor/new";
	}
	
	//METODO PARA GUARDAR EL COMPETIDOR AL EVENTO
	@PostMapping("event-competidor-save")
	public String eventCompetitorSave(/*@PathVariable("id") Integer id,*/ @ModelAttribute("eventUser") EventUser eventUser, SessionStatus status) {
		//System.out.println(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails primaryuserDetail = (UserDetails) auth.getPrincipal();
        Primaryuser primaryUser = new Primaryuser();
        
		try {
			//Optional<Event> idevent = eventService.findById(event.getId());
			//Optional<User> iduser = competitorService.findById(competitor.getId());
			//Integer competitor1 = idevent.get().getId();
			//Integer event1 = iduser.get().getId();
			//if (idevent.isPresent() || iduser.isPresent()) {
				
				//System.out.println(competitor1);
				//Optional<User> getUser = eventCompetitorService.findByEvent(competitor1);
				//eventUser.getUser().setId(competitor1);
				//eventUser.getEvent().setId(event1);
				//System.out.println(event1);
			Random r = new Random();
			Integer aleatorio = r.nextInt(2);
			System.out.println(aleatorio);
			User competitor = new User();
	        Event event = new Event();
	        event.setId(aleatorio);
	        competitor.setId(aleatorio);
	        eventUser.setUser(competitor);
	        eventUser.setEvent(event);
			
				eventCompetitorService.save(eventUser);
				status.setComplete();
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/events";
	}
	
}
