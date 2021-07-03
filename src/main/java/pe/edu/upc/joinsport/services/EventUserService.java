package pe.edu.upc.joinsport.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsport.models.entities.EventUser;

public interface EventUserService extends CrudService<EventUser, Integer>{
	
	Optional<EventUser> findByUser(Integer user) throws Exception;

	Optional<EventUser> findByEvent(Integer event) throws Exception; // LUEGO SE QUITA PARA CUANDO SE IMPLEMENTA EL SECURITY
}
