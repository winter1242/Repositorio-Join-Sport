package pe.edu.upc.joinsport.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsport.models.entities.Organizer;

public interface OrganizerService extends CrudService<Organizer, Integer>{
	List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception;
	List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception;
	Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception; 
	//Optional<Organizer>findByOrganizer(String userOrganizer) throws Exception;
}
