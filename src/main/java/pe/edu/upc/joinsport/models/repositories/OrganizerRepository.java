package pe.edu.upc.joinsport.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer>{
	List<Organizer> findByNameOrganizerContaining(String nameOrganizer) throws Exception;
	List<Organizer> findBySurnameOrganizerContaining(String surnameOrganizer) throws Exception;
	Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception; 
	// puede ocurrir error en el "dniOrganizer"
	//Optional<Organizer>findByPrimaryuser(String primaryuserOrganizer) throws Exception;
}
