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

import pe.edu.upc.joinsports.models.entities.Organizer;
import pe.edu.upc.joinsports.models.respositories.OrganizerRepository;

@Named
@ApplicationScoped
public class OrganizerRepositoryImpl implements OrganizerRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;

	@Override
	public Organizer save(Organizer entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Organizer update(Organizer entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Organizer> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Organizer> findById(Integer id) throws Exception {
		Optional<Organizer> optional = Optional.empty();

		String qlString = "SELECT o FROM Organizer o WHERE o.id = ?1";
		TypedQuery<Organizer> query = em.createQuery(qlString, Organizer.class);
		query.setParameter(1, id);

		Organizer organizer = query.getSingleResult();
		if (organizer != null) {
			optional = Optional.of(organizer);
		}
		return optional;
	}

	@Override
	public List<Organizer> findAll() throws Exception {
		List<Organizer> organizers = new ArrayList<>();

		String qlString = "SELECT o FROM Organizer o";
		TypedQuery<Organizer> query = em.createQuery(qlString, Organizer.class);

		organizers = query.getResultList();
		return organizers;
	}

	@Override
	public List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception {
		List<Organizer> organizers = new ArrayList<>();
		String qlString = "SELECT o FROM Organizer o WHERE o.nameOrganizer LIKE ?1"; 
		TypedQuery<Organizer> query = em.createQuery(qlString, Organizer.class);
		query.setParameter(1, nameOrganizer);
		organizers = query.getResultList();
		return organizers;
	}

	@Override
	public List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception {
		List<Organizer> organizers = new ArrayList<>();
		String qlString = "SELECT o FROM Organizer o WHERE o.surnameOrganizer LIKE '?1'"; 
		TypedQuery<Organizer> query = em.createQuery(qlString, Organizer.class);
		query.setParameter(1, surnameOrganizer.toUpperCase());
		organizers = query.getResultList();
		return organizers;
	}

	@Override
	public Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception {
		
		Optional<Organizer> optional = Optional.empty();
		String qlString = "SELECT o FROM Organizer o WHERE o.dniOrganizer = ?1";
		TypedQuery<Organizer> query = em.createQuery(qlString, Organizer.class);
		query.setParameter(1, dniOrganizer);
		Organizer organizer = query.getResultList().stream().findFirst().orElse(null);
		if (organizer != null) {
			optional = Optional.of(organizer);
		}
		return optional;
	}

}
