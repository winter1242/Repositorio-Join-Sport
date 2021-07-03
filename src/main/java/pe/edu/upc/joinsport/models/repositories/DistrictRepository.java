package pe.edu.upc.joinsport.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{
	List<District> findByNameDistrictLike(String nameDistrict) throws Exception;
}
