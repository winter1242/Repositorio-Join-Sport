package pe.edu.upc.joinsport.services;

import java.util.List;

import pe.edu.upc.joinsport.models.entities.District;

public interface DistrictService extends CrudService<District, Integer>{
	List<District> findByNameDistrict(String nameDistrict) throws Exception;
}
