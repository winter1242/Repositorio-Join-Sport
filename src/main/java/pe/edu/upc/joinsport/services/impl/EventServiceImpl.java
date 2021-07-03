package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.Event;
import pe.edu.upc.joinsport.models.repositories.EventRepository;
import pe.edu.upc.joinsport.services.EventService;

@Service
public class EventServiceImpl implements EventService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EventRepository eventRepository;

	@Transactional
	@Override
	public Event save(Event entity) throws Exception {
		return eventRepository.save(entity);
	}

	@Transactional
	@Override
	public Event update(Event entity) throws Exception {
		return eventRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		eventRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Event> findById(Integer id) throws Exception {
		return eventRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Event> findAll() throws Exception {
		return eventRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Event> findByNameEvent(String nameEvent) throws Exception {
		return eventRepository.findByNameEventLike(nameEvent);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Event> findByNameDistrict(String nameDistrict) throws Exception {
		return eventRepository.findByNameDistrict(nameDistrict);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Event> findByNameSport(String nameSport) throws Exception {
		return eventRepository.findByNameSport(nameSport);
	}

}
