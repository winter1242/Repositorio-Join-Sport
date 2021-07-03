package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.User;
import pe.edu.upc.joinsport.models.repositories.UserRepository;
import pe.edu.upc.joinsport.services.UserService;

@Service
public class UserServiceImpl implements UserService,Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
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
		return userRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<User> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findByNameUser(String nameUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findByNameUserContaining(nameUser);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findBySurnameUser(String surnameUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findBySurnameUserContaining(surnameUser);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<User> findBydniUser(String dniUser) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findBydniUser(dniUser);
	}

}
