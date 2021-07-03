package pe.edu.upc.joinsport.init;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.entities.User;
import pe.edu.upc.joinsport.models.repositories.OrganizerRepository;
import pe.edu.upc.joinsport.models.repositories.PrimaryuserRepository;
import pe.edu.upc.joinsport.models.repositories.UserRepository;
import pe.edu.upc.joinsport.utils.Segmento;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private OrganizerRepository organizerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrimaryuserRepository primaryuserRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		// SOLO DESBLOQUEAR CUANDO SE CREEN USUARIOS DE FORMA MANUAL
		/*
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String password = bcpe.encode("organizer");
		
		Primaryuser organizer1 = new Primaryuser();
		organizer1.setId(1);
		organizer1.setUsername("organizer1");
		organizer1.setPassword( password );
		organizer1.setSegmento(Segmento.ORGANIZER);
		organizer1.setIdSegmento(1);
		organizer1.setEnable(true);
		
		Primaryuser organizer2 = new Primaryuser();
		organizer2.setId(2);
		organizer2.setUsername("organizer2");
		organizer2.setPassword( password );
		organizer2.setSegmento(Segmento.ORGANIZER);
		organizer2.setIdSegmento(2);
		organizer2.setEnable(true);
		
		// Creando el objeto que cifra las contraseñas
		BCryptPasswordEncoder bcpeCompetitor = new BCryptPasswordEncoder();
		String passwordCompetitor = bcpeCompetitor.encode("competitor");
		
		Primaryuser competitor1 = new Primaryuser();
		competitor1.setId(3);
		competitor1.setUsername("competitor1");
		competitor1.setPassword(passwordCompetitor);
		competitor1.setSegmento(Segmento.COMPETITOR);
		competitor1.setIdSegmento(3);
		competitor1.setEnable(true);
		
		Primaryuser competitor2 = new Primaryuser();
		competitor2.setId(4);
		competitor2.setUsername("competitor2");
		competitor2.setPassword(passwordCompetitor);
		competitor2.setSegmento(Segmento.COMPETITOR);
		competitor2.setIdSegmento(4);
		competitor2.setEnable(true);
		
		// Roles de usuario: ROLE_ORGANIZER, ROLE_COMPETITOR
		organizer1.addAuthority("ROLE_ORGANIZER");
		organizer2.addAuthority("ROLE_ORGANIZER");
		competitor1.addAuthority("ROLE_COMPETITOR");
		competitor2.addAuthority("ROLE_COMPETITOR");
		
		// Accesos a recursos
		organizer1.addAuthority("ACCESS_VIEW_ORGANIZER");
		organizer1.addAuthority("ACCESS_TYPES_OF_PAYMENTS");
		organizer2.addAuthority("ACCESS_VIEW_ORGANIZER");
		organizer2.addAuthority("ACCESS_TYPES_OF_PAYMENTS");
		
		competitor1.addAuthority("ACCESS_VIEW_EVENTS");
		competitor2.addAuthority("ACCESS_VIEW_EVENTS");
		
		primaryuserRepository.save(organizer1);
		primaryuserRepository.save(organizer2);
		
		primaryuserRepository.save(competitor1);
		primaryuserRepository.save(competitor2);
		*/
		
		/*-----------------------------------------------------------------------------------------------------*/
		/*
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		Optional<Organizer> optional = organizerRepository.findById(1);
		if(optional.isPresent()) {
			Organizer angel = optional.get();
			Primaryuser organizador1 = new Primaryuser("tOPleTr", bcpe.encode("15301"), angel);
			// ROLE -> Segmento Objetivo
			organizador1.addAuthority("ROLE_ORGANIZER");
			// ACCESS -> 
			organizador1.addAuthority("ACCESS_VIEW_ORGANIZER"); //PARA VER OTROS ORGANIZADORES
			organizador1.addAuthority("ACCESS_TYPES_OF_PAYMENTS"); // PARA TIPOS DE PAGOS
			primaryuserRepository.save(organizador1);
		}
		
		Optional<User> optional2 = userRepository.findById(1);
		if(optional2.isPresent()) {
			User kevin = optional2.get();
			Primaryuser participante1 = new Primaryuser("ElAmorosox99", bcpe.encode("Roly1234"), kevin);
			// ROLE -> Segmento Objetivo
			participante1.addAuthority("ROLE_COMPETITOR");
			// ACCESS -> 
			participante1.addAuthority("ACCESS_VIEW_EVENTS"); // PARA VER EVENTOS
			participante1.addAuthority("ACCESS_VIEW_ORGANIZERS"); // PARA VER LOS ORGANIZADORES
			primaryuserRepository.save(participante1);
		}*/
		
	}
}
