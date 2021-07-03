package pe.edu.upc.joinsport.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsport.models.entities.Primaryuser;

public interface PrimeryService extends CrudService<Primaryuser, Integer>{
	Optional<Primaryuser> findByUsername(String username);
	
	List<Primaryuser>findAllOrderByIdDesc() throws Exception;
}
