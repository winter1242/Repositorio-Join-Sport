package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.Organizer;
import pe.edu.upc.joinsports.models.respositories.OrganizerRepository;
import pe.edu.upc.joinsports.services.OrganizerService;

@Named
@ApplicationScoped
public class OrganizerServiceImpl implements OrganizerService,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
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
		return organizerRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		organizerRepository.deleteById(id);
	}

	@Override
	public Optional<Organizer> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findById(id);
	}

	@Override
	public List<Organizer> findAll() throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findAll();
	}

	@Override
	public List<Organizer> findByNameOrganizer(String nameOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findByNameOrganizer(nameOrganizer);
	}

	@Override
	public List<Organizer> findBySurnameOrganizer(String surnameOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findBySurnameOrganizer(surnameOrganizer);
	}

	@Override
	public Optional<Organizer> findBydniOrganizer(String dniOrganizer) throws Exception {
		// TODO Auto-generated method stub
		return organizerRepository.findBydniOrganizer(dniOrganizer);
	}

}
