package pe.edu.upc.joinsports.services;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.Event;

public interface EventService extends CrudService<Event, Integer>{
	List<Event> findByNameEvent(String nameEvent) throws Exception;
}
