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

import pe.edu.upc.joinsports.models.entities.User;
import pe.edu.upc.joinsports.models.respositories.UserRepository;

@Named
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "joinsportsPU")
	private EntityManager em;

	@Override
	public User save(User entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public User update(User entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<User> optional = findById(id);
		if (optional.isPresent()) {
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<User> findById(Integer id) throws Exception {
		Optional<User> optional = Optional.empty();

		String qlString = "SELECT u FROM User u WHERE u.id = ?1";
		TypedQuery<User> query = em.createQuery(qlString, User.class);
		query.setParameter(1, id);

		User user = query.getSingleResult();
		if (user != null) {
			optional = Optional.of(user);
		}
		return optional;
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> users = new ArrayList<>();

		String qlString = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(qlString, User.class);

		users = query.getResultList();
		return users;
	}

	@Override
	public List<User> findByNameUser(String nameUser) throws Exception {
		List<User> users = new ArrayList<>();
		String qlString = "SELECT u FROM User u WHERE u.nameUser LIKE ?1";
		TypedQuery<User> query = em.createQuery(qlString, User.class);
		query.setParameter(1, nameUser);
		users = query.getResultList();
		return users;
	}

	@Override
	public List<User> findBySurnameUser(String surnameUser) throws Exception {
		List<User> users = new ArrayList<>();
		String qlString = "SELECT u FROM User u WHERE u.surnameUser LIKE ?1";
		TypedQuery<User> query = em.createQuery(qlString, User.class);
		query.setParameter(1, surnameUser);
		users = query.getResultList();
		return users;
	}

	@Override
	public Optional<User> findBydniUser(String dniUser) throws Exception {
		// Declara la variable a retornar
		Optional<User> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT u FROM User u WHERE u.dniUser = ?1";
		// Crear la consulta
		TypedQuery<User> query = em.createQuery(qlString, User.class);
		// Establer los paremetros
		query.setParameter(1,dniUser);
		// Obtener el resultado de la consulta
		User user = query.getResultList().stream().findFirst().orElse(null);
		// Verificar la existencia del objeto
		if (user != null) {
			// Agregando el objeto cliente al Optional
			optional = Optional.of(user);
		}
		return optional;
	}

}
