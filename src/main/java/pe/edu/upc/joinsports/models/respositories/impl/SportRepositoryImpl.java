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

import pe.edu.upc.joinsports.models.entities.Sport;
import pe.edu.upc.joinsports.models.respositories.SportRepository;

@Named
@ApplicationScoped
public class SportRepositoryImpl implements SportRepository,Serializable{
 
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;
	
	@Override
	public Sport save(Sport entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Sport update(Sport entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Sport> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Sport> findById(Integer id) throws Exception {
		Optional<Sport> optional = Optional.empty();

		String qlString = "SELECT s FROM Sport s WHERE s.id = ?1";
		TypedQuery<Sport> query = em.createQuery(qlString, Sport.class);
		query.setParameter(1, id);

		Sport sport = query.getSingleResult();
		if (sport != null) {
			optional = Optional.of(sport);
		}
		return optional;
	}

	@Override
	public List<Sport> findAll() throws Exception {
		List<Sport> sports = new ArrayList<>();

		String qlString = "SELECT s FROM Sport s";
		TypedQuery<Sport> query = em.createQuery(qlString, Sport.class);

		sports = query.getResultList();
		return sports;
	}

	@Override
	public List<Sport> findByNameSport(String nameSport) throws Exception {
		List<Sport> sports = new ArrayList<>();
		String qlString = "SELECT s FROM Sport s WHERE s.nameSport LIKE '%?1%'"; 
		TypedQuery<Sport> query = em.createQuery(qlString, Sport.class);
		query.setParameter(1, nameSport);
		sports = query.getResultList();
		return sports;
	}

}
