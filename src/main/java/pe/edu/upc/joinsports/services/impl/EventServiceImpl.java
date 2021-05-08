package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.Event;
import pe.edu.upc.joinsports.models.respositories.EventRepository;
import pe.edu.upc.joinsports.services.EventService;

@Named
@ApplicationScoped
public class EventServiceImpl implements EventService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventRepository eventRepository;

	@Transactional
	@Override
	public Event save(Event entity) throws Exception {
		return eventRepository.save(entity);
	}

	@Transactional
	@Override
	public Event update(Event entity) throws Exception {
		return eventRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		eventRepository.deleteById(id);
	}

	@Override
	public Optional<Event> findById(Integer id) throws Exception {
		return eventRepository.findById(id);
	}

	@Override
	public List<Event> findAll() throws Exception {
		return eventRepository.findAll();
	}

	@Override
	public List<Event> findByNameEvent(String nameEvent) throws Exception {
		return eventRepository.findByNameEvent(nameEvent);
	}

}
