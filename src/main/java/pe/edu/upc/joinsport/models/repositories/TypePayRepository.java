package pe.edu.upc.joinsport.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.joinsport.models.entities.TypePay;

@Repository
public interface TypePayRepository extends JpaRepository<TypePay, Integer>{
	List<TypePay> findByNameTypePayLike(String nameTypePay) throws Exception;
}
