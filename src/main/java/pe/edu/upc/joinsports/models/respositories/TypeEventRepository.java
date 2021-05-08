package pe.edu.upc.joinsports.models.respositories;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.TypeEvent;

public interface TypeEventRepository extends JpaRepository<TypeEvent, Integer>{
	List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception;
}