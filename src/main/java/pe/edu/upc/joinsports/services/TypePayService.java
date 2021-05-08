package pe.edu.upc.joinsports.services;

import java.util.List;

import pe.edu.upc.joinsports.models.entities.TypePay;

public interface TypePayService extends CrudService<TypePay, Integer>{
	List<TypePay> findByNameTypePay(String nameTypePay) throws Exception;
}
