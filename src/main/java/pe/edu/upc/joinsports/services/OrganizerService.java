package pe.edu.upc.joinsports.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsports.models.entities.Organizer;

public interface OrganizerService extends CrudService<Organizer, Integer>{
	List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception;
	List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception;
	Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception; 
}
