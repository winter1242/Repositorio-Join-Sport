package pe.edu.upc.joinsport.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByNameUserContaining(String nameUser) throws Exception;
	List<User> findBySurnameUserContaining(String surnameUser) throws Exception;
	Optional<User> findBydniUser(String dniUser) throws Exception; 
}