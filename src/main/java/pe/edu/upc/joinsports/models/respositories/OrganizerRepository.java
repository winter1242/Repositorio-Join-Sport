package pe.edu.upc.joinsports.models.respositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsports.models.entities.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer>{
	List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception;
	List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception;
	Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception; 
	// puede ocurrir error en el "dniOrganizer"
}
