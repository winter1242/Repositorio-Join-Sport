package pe.edu.upc.joinsports.models.respositories;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.TypePay;

public interface TypePayRepository extends JpaRepository<TypePay, Integer>{
	List<TypePay> findByNameTypePay(String nameTypePay) throws Exception;
}
