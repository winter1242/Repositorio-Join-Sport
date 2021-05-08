package pe.edu.upc.joinsports.models.respositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.joinsports.models.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByNameUser(String nameUser) throws Exception;
	List<User> findBySurnameUser(String surnameUser) throws Exception;
	Optional<User> findBydniUser(String dniUser) throws Exception; 
}
