package pe.edu.upc.joinsports.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsports.models.entities.User;

public interface UserService extends CrudService<User, Integer>{
	List<User> findByNameUser(String nameUser) throws Exception;
	List<User> findBySurnameUser(String surnameUser) throws Exception;
	Optional<User> findBydniUser(String dniUser) throws Exception; 
}
