package pe.edu.upc.joinsports.models.respositories;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer>{
	List<Sport> findByNameSport(String nameSport) throws Exception;
}
