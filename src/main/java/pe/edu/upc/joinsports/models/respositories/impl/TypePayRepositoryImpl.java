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

import pe.edu.upc.joinsports.models.entities.TypePay;
import pe.edu.upc.joinsports.models.respositories.TypePayRepository;

@Named
@ApplicationScoped
public class TypePayRepositoryImpl implements TypePayRepository,Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;
	
	@Override
	public TypePay save(TypePay entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public TypePay update(TypePay entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<TypePay> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<TypePay> findById(Integer id) throws Exception {
		Optional<TypePay> optional = Optional.empty();

		String qlString = "SELECT tp FROM TypePay tp WHERE tp.id = ?1";
		TypedQuery<TypePay> query = em.createQuery(qlString, TypePay.class);
		query.setParameter(1, id);

		TypePay typePay = query.getSingleResult();
		if (typePay != null) {
			optional = Optional.of(typePay);
		}
		return optional;
	}

	@Override
	public List<TypePay> findAll() throws Exception {
		List<TypePay> typePays = new ArrayList<>();

		String qlString = "SELECT tp FROM TypePay tp";
		TypedQuery<TypePay> query = em.createQuery(qlString, TypePay.class);

		typePays = query.getResultList();
		return typePays;
	}

	@Override
	public List<TypePay> findByNameTypePay(String nameTypePay) throws Exception {
		List<TypePay> typePays = new ArrayList<>();
		String qlString = "SELECT tp FROM TypePay tp WHERE tp.nameTypePay LIKE '%?1%'";         
		TypedQuery<TypePay> query = em.createQuery(qlString, TypePay.class);
		query.setParameter(1, nameTypePay);
		typePays = query.getResultList();
		return typePays;
	}

}
