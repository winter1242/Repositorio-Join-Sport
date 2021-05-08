package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.TypePay;
import pe.edu.upc.joinsports.models.respositories.TypePayRepository;
import pe.edu.upc.joinsports.services.TypePayService;

@Named
@ApplicationScoped
public class TypePayServiceImpl implements TypePayService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
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
		return typePayRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		typePayRepository.deleteById(id);
	}

	@Override
	public Optional<TypePay> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findById(id);
	}

	@Override
	public List<TypePay> findAll() throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findAll();
	}

	@Override
	public List<TypePay> findByNameTypePay(String nameTypePay) throws Exception {
		// TODO Auto-generated method stub
		return typePayRepository.findByNameTypePay(nameTypePay);
	}

}
