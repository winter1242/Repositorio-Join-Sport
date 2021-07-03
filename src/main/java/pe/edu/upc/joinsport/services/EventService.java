package pe.edu.upc.joinsport.services;

import java.util.List;

import pe.edu.upc.joinsport.models.entities.Event;

public interface EventService extends CrudService<Event, Integer>{
	List<Event> findByNameEvent(String nameEvent) throws Exception;
	List<Event> findByNameDistrict (String nameDistrict) throws Exception;
	List<Event> findByNameSport (String nameSport) throws Exception;
}
