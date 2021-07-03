package pe.edu.upc.joinsport.services;

import java.util.List;

import pe.edu.upc.joinsport.models.entities.TypePay;

public interface TypePayService extends CrudService<TypePay, Integer>{
	List<TypePay> findByNameTypePay(String nameTypePay) throws Exception;
}
