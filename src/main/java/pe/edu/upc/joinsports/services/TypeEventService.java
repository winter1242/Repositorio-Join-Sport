package pe.edu.upc.joinsports.services;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.TypeEvent;

public interface TypeEventService extends CrudService<TypeEvent, Integer>{
	List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception;
}
