package pe.edu.upc.joinsports.models.respositories;

import java.util.Date;
import java.util.List;

import pe.edu.upc.joinsports.models.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	List<Event> findByNameEvent(String nameEvent) throws Exception;
	//List<Event> findByDayEventStartAndDayEventEnd(Date dayEventStart, Date dayEventEnd) throws Exception;
	//probando la para encontrar una lista entre los días de inicio y fin (importante)
}
