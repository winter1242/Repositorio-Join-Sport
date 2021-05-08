package pe.edu.upc.joinsports.services;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.Sport;

public interface SportService extends CrudService<Sport, Integer>{
	List<Sport> findByNameSport(String nameSport) throws Exception;
}
