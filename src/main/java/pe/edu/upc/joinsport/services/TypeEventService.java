package pe.edu.upc.joinsport.services;

import java.util.List;

import pe.edu.upc.joinsport.models.entities.TypeEvent;

public interface TypeEventService extends CrudService<TypeEvent, Integer>{
	List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception;
}
