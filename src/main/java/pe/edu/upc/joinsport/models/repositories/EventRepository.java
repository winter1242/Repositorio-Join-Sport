package pe.edu.upc.joinsport.models.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	List<Event> findByNameEventLike(String nameEvent) throws Exception;
	
	@Query("SELECT e FROM Event e WHERE e.district.nameDistrict LIKE %?1%")
	List<Event> findByNameDistrict (String nameDistrict) throws Exception;
	
	@Query("SELECT e FROM Event e WHERE e.sport.nameSport LIKE %?1%")
	List<Event> findByNameSport (String nameSport) throws Exception;
	//List<Event> findByDayEventStartAndDayEventEnd(Date dayEventStart, Date dayEventEnd) throws Exception;
	//probando la para encontrar una lista entre los d�as de inicio y fin (importante)
}
