package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.TypePay;
import pe.edu.upc.joinsport.models.repositories.TypePayRepository;
import pe.edu.upc.joinsport.services.TypePayService;

@Service
public class TypePayServiceImpl implements TypePayService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TypePayRepository typePayRepository;

	@Transactional
	@Override
	public TypePay save(TypePay entity) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.save(entity);
	}

	@Transactional
	@Override
	public TypePay update(TypePay entity) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		typePayRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<TypePay> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TypePay> findAll() throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<TypePay> findByNameTypePay(String nameTypePay) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findByNameTypePayLike(nameTypePay);
	}

}
