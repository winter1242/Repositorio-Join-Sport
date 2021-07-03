package pe.edu.upc.joinsport.services;

import java.util.List;

import pe.edu.upc.joinsport.models.entities.Sport;

public interface SportService extends CrudService<Sport, Integer>{
	List<Sport> findByNameSport(String nameSport) throws Exception;
}
