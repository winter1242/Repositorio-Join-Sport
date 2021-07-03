package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.EventUser;
import pe.edu.upc.joinsport.models.repositories.EventUserRepository;
import pe.edu.upc.joinsport.services.EventUserService;

@Service
public class EventUserServiceImpl implements EventUserService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EventUserRepository eventUserRepository;
	
	@Transactional
	@Override
	public EventUser save(EventUser entity) throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.save(entity);
	}

	@Transactional
	@Override
	public EventUser update(EventUser entity) throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		eventUserRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<EventUser> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EventUser> findAll() throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<EventUser> findByUser(Integer user) throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.findByUser(user);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<EventUser> findByEvent(Integer event) throws Exception {
		// TODO Auto-generated method stub
		return eventUserRepository.findByEvent(event);
	}

}
