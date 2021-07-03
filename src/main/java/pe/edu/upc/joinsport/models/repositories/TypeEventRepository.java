package pe.edu.upc.joinsport.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.TypeEvent;

@Repository
public interface TypeEventRepository extends JpaRepository<TypeEvent, Integer>{
	List<TypeEvent> findByNameTypeEventLike(String nameTypeEvent) throws Exception;
}