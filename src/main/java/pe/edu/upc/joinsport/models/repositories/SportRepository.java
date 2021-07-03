package pe.edu.upc.joinsport.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer>{
	List<Sport> findByNameSportContaining(String nameSport) throws Exception;
}
