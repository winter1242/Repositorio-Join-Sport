package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.User;
import pe.edu.upc.joinsports.models.respositories.UserRepository;
import pe.edu.upc.joinsports.services.UserService;

@Named
@ApplicationScoped
public class UserServiceImpl implements UserService,Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User save(User entity) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.save(entity);
	}

	@Transactional
	@Override
	public User update(User entity) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<User> findByNameUser(String nameUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findByNameUser(nameUser);
	}

	@Override
	public List<User> findBySurnameUser(String surnameUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findBySurnameUser(surnameUser);
	}

	@Override
	public Optional<User> findBydniUser(String dniUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findBydniUser(dniUser);
	}

}
