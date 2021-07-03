package pe.edu.upc.joinsport.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.EventUser;

@Repository
public interface EventUserRepository extends JpaRepository<EventUser, Integer>{
	@Query("SELECT eu.id FROM EventUser eu JOIN User u ON eu.user.id=u.id where eu.user.id = ?1")
	Optional<EventUser>findByUser(Integer user) throws Exception;
	
	@Query("SELECT eu.id FROM EventUser eu JOIN Event e ON eu.event.id=e.id where eu.event.id = ?1")
	Optional<EventUser>findByEvent(Integer event) throws Exception; //LUEGO SE QUITA PARA CUANDO SE IMPLEMENTA EL SECURITY
}
