package pe.edu.upc.joinsports.models.respositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.joinsports.models.entities.Event;
import pe.edu.upc.joinsports.models.respositories.EventRepository;

@Named
@ApplicationScoped
public class EventRepositoryImpl implements EventRepository,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;
	
	@Override
	public Event save(Event entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Event update(Event entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Event> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Event> findById(Integer id) throws Exception {
		Optional<Event> optional = Optional.empty();

		String qlString = "SELECT e FROM Event e WHERE e.id = ?1";
		TypedQuery<Event> query = em.createQuery(qlString, Event.class);
		query.setParameter(1, id);

		Event event = query.getSingleResult();
		if (event != null) {
			optional = Optional.of(event);
		}
		return optional;
	}

	@Override
	public List<Event> findAll() throws Exception {
		List<Event> events = new ArrayList<>();

		String qlString = "SELECT e FROM Event e";
		TypedQuery<Event> query = em.createQuery(qlString, Event.class);

		events = query.getResultList();
		return events;
	}

	@Override
	public List<Event> findByNameEvent(String nameEvent) throws Exception {
		List<Event> events = new ArrayList<>();
		String qlString = "SELECT e FROM Event e WHERE e.nameEvent LIKE '%?1%'";             
		TypedQuery<Event> query = em.createQuery(qlString, Event.class);
		query.setParameter(1, nameEvent);
		events = query.getResultList();
		return events;
	}

}
