package pe.edu.upc.joinsport.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.Primaryuser;

@Repository
public interface PrimaryuserRepository extends JpaRepository<Primaryuser, Integer>{
	Optional<Primaryuser> findByUsername(String username);
}
