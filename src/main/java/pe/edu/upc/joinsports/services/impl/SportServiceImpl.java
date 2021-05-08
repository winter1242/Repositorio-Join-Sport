package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.Sport;
import pe.edu.upc.joinsports.models.respositories.SportRepository;
import pe.edu.upc.joinsports.services.SportService;

@Named
@ApplicationScoped
public class SportServiceImpl implements SportService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
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
		return sportRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		sportRepository.deleteById(id);
	}

	@Override
	public Optional<Sport> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findById(id);
	}

	@Override
	public List<Sport> findAll() throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findAll();
	}

	@Override
	public List<Sport> findByNameSport(String nameSport) throws Exception {
		// TODO Auto-generated method stub
		return sportRepository.findByNameSport(nameSport);
	}

}
