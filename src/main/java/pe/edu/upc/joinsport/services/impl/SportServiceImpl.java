package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.Sport;
import pe.edu.upc.joinsport.models.repositories.SportRepository;
import pe.edu.upc.joinsport.services.SportService;

@Service
public class SportServiceImpl implements SportService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SportRepository sportRepository;

	@Transactional
	@Override
	public Sport save(Sport entity) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.save(entity);
	}

	@Transactional
	@Override
	public Sport update(Sport entity) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		sportRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Sport> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Sport> findAll() throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Sport> findByNameSport(String nameSport) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findByNameSportContaining(nameSport);
	}

}
