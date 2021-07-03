package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.Organizer;
import pe.edu.upc.joinsport.models.repositories.OrganizerRepository;
import pe.edu.upc.joinsport.services.OrganizerService;

@Service
public class OrganizerServiceImpl implements OrganizerService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrganizerRepository organizerRepository;

	@Transactional
	@Override
	public Organizer save(Organizer entity) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.save(entity);
	}

	@Transactional
	@Override
	public Organizer update(Organizer entity) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		organizerRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Organizer> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Organizer> findAll() throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findByNameOrganizerContaining(nameOrganizer);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findBySurnameOrganizerContaining(surnameOrganizer);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findBydniOrganizer(dniOrganizer);
	}

	/*@Transactional(readOnly = true)
	@Override
	public Optional<Organizer> findByOrganizer(String userOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findByOrganizer(userOrganizer);
	}*/

}
