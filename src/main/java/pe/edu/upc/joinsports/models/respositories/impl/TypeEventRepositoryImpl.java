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

import pe.edu.upc.joinsports.models.entities.TypeEvent;
import pe.edu.upc.joinsports.models.respositories.TypeEventRepository;

@Named
@ApplicationScoped
public class TypeEventRepositoryImpl implements TypeEventRepository,Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;
	
	@Override
	public TypeEvent save(TypeEvent entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public TypeEvent update(TypeEvent entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<TypeEvent> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<TypeEvent> findById(Integer id) throws Exception {
		Optional<TypeEvent> optional = Optional.empty();

		String qlString = "SELECT te FROM TypeEvent te WHERE te.id = ?1";
		TypedQuery<TypeEvent> query = em.createQuery(qlString, TypeEvent.class);
		query.setParameter(1, id);

		TypeEvent typeEvent = query.getSingleResult();
		if (typeEvent != null) {
			optional = Optional.of(typeEvent);
		}
		return optional;
	}

	@Override
	public List<TypeEvent> findAll() throws Exception {
		List<TypeEvent> typeEvents = new ArrayList<>();

		String qlString = "SELECT te FROM TypeEvent te";
		TypedQuery<TypeEvent> query = em.createQuery(qlString, TypeEvent.class);

		typeEvents = query.getResultList();
		return typeEvents;
	}

	@Override
	public List<TypeEvent> findByNameTypeEvent(String nameTypeEvent) throws Exception {
		List<TypeEvent> typeEvents = new ArrayList<>();
		String qlString = "SELECT te FROM TypeEvent te WHERE te.nameTypeEvent LIKE '%?1%'";         
		TypedQuery<TypeEvent> query = em.createQuery(qlString, TypeEvent.class);
		query.setParameter(1, nameTypeEvent);
		typeEvents = query.getResultList();
		return typeEvents;
	}

}
