package pe.edu.upc.joinsports.services;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.District;

public interface DistrictService extends CrudService<District, Integer>{
	List<District> findByNameDistrict(String nameDistrict) throws Exception;
}
