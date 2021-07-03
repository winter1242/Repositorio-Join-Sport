package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.repositories.PrimaryuserRepository;
import pe.edu.upc.joinsport.services.PrimeryService;

@Service
public class PrimaryServiceImpl implements PrimeryService, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private PrimaryuserRepository primaryRepository;
	
	@Transactional
	@Override
	public Primaryuser save(Primaryuser entity) throws Exception {
		// TODO Auto-generated method stub
		return primaryRepository.save(entity);
	}

	@Transactional
	@Override
	public Primaryuser update(Primaryuser entity) throws Exception {
		// TODO Auto-generated method stub
		return primaryRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		primaryRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Primaryuser> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return primaryRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Primaryuser> findAll() throws Exception {
		// TODO Auto-generated method stub
		return primaryRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Primaryuser> findByUsername(String username) {
		// TODO Auto-generated method stub
		return primaryRepository.findByUsername(username);
	}

	@Override
	public List<Primaryuser> findAllOrderByIdDesc() throws Exception {
		// TODO Auto-generated method stub
		return primaryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
	}
}
