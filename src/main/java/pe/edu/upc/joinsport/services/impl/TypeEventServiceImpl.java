package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.TypeEvent;
import pe.edu.upc.joinsport.models.repositories.TypeEventRepository;
import pe.edu.upc.joinsport.services.TypeEventService;

@Service
public class TypeEventServiceImpl implements TypeEventService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TypeEventRepository typeEventRepository;

	@Transactional
	@Override
	public TypeEvent save(TypeEvent entity) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.save(entity);
	}

	@Transactional
	@Override
	public TypeEvent update(TypeEvent entity) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		typeEventRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<TypeEvent> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TypeEvent> findAll() throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findByNameTypeEventLike(nameTypeEvent);
	}

}
