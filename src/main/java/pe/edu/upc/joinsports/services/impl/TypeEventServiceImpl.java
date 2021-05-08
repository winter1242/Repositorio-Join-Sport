package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.TypeEvent;
import pe.edu.upc.joinsports.models.respositories.TypeEventRepository;
import pe.edu.upc.joinsports.services.TypeEventService;

@Named
@ApplicationScoped
public class TypeEventServiceImpl implements TypeEventService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
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
		return typeEventRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		typeEventRepository.deleteById(id);
	}

	@Override
	public Optional<TypeEvent> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findById(id);
	}

	@Override
	public List<TypeEvent> findAll() throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findAll();
	}

	@Override
	public List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception {
		// TODO Auto-generated method stub
		return typeEventRepository.findByNameTypeEvent(nameTypeEvent);
	}

}
