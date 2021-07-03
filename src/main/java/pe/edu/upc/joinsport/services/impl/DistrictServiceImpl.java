package pe.edu.upc.joinsport.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.joinsport.models.entities.District;
import pe.edu.upc.joinsport.models.repositories.DistrictRepository;
import pe.edu.upc.joinsport.services.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DistrictRepository disctricRepository;

	@Transactional
	@Override
	public District save(District entity) throws Exception {
		return disctricRepository.save(entity);
	}

	@Transactional
	@Override
	public District update(District entity) throws Exception {
		return disctricRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		disctricRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<District> findById(Integer id) throws Exception {
		return disctricRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<District> findAll() throws Exception {
		return disctricRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<District> findByNameDistrict(String nameDistrict) throws Exception {
		return disctricRepository.findByNameDistrictLike(nameDistrict);
	}

}
