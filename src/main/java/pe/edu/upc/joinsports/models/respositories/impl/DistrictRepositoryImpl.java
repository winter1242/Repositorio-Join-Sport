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

import pe.edu.upc.joinsports.models.entities.District;
import pe.edu.upc.joinsports.models.respositories.DistrictRepository;

@Named
@ApplicationScoped
public class DistrictRepositoryImpl implements DistrictRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;

	@Override
	public District save(District entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public District update(District entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<District> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<District> findById(Integer id) throws Exception {
		Optional<District> optional = Optional.empty();

		String qlString = "SELECT d FROM District d WHERE d.id = ?1";
		TypedQuery<District> query = em.createQuery(qlString, District.class);
		query.setParameter(1, id);

		District district = query.getSingleResult();
		if (district != null) {
			optional = Optional.of(district);
		}
		return optional;
	}

	@Override
	public List<District> findAll() throws Exception {
		List<District> districts = new ArrayList<>();

		String qlString = "SELECT d FROM District d";
		TypedQuery<District> query = em.createQuery(qlString, District.class);

		districts = query.getResultList();
		return districts;
	}

	@Override
	public List<District> findByNameDistrict(String nameDistrict) throws Exception {
		List<District> districts = new ArrayList<>();
		String qlString = "SELECT d FROM District d WHERE d.nameDistrict LIKE '%?1%'"; //posible error en "name" quisa "nameDistrict"             
		TypedQuery<District> query = em.createQuery(qlString, District.class);
		query.setParameter(1, nameDistrict);
		districts = query.getResultList();
		return districts;
	}

}
