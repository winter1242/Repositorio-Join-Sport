package pe.edu.upc.joinsports.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.joinsports.models.entities.District;
import pe.edu.upc.joinsports.models.respositories.DistrictRepository;
import pe.edu.upc.joinsports.services.DistrictService;

@Named
@ApplicationScoped
public class DistrictServiceImpl implements DistrictService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DistrictRepository disctricRepository;

	@Transactional
	@Override
	public District save(District entity) throws Exception {
		return disctricRepository.save(entity);
	}

	@Transactional
	@Override
	public District update(District entity) throws Exception {
		return disctricRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		disctricRepository.deleteById(id);
	}

	@Override
	public Optional<District> findById(Integer id) throws Exception {
		return disctricRepository.findById(id);
	}

	@Override
	public List<District> findAll() throws Exception {
		return disctricRepository.findAll();
	}

	@Override
	public List<District> findByNameDistrict(String nameDistrict) throws Exception {
		return disctricRepository.findByNameDistrict(nameDistrict);
	}

}
