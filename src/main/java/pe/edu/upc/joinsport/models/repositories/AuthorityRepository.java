package pe.edu.upc.joinsport.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.joinsport.models.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
