package pe.edu.upc.joinsports.models.respositories;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.District;

public interface DistrictRepository extends JpaRepository<District, Integer>{
	List<District> findByNameDistrict(String nameDistrict) throws Exception;
}
