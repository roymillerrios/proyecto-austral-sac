package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.IMaterialDao;
import com.g5.app.models.entity.Material;

@Service
public class MaterialServiceImpl implements IMaterialService {

	@Autowired
	private IMaterialDao materialDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findAll(){
		//
		return (List<Material>) materialDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Material material) {
		materialDao.save(material);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Material findOne(Long id) {
		return materialDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		materialDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Material> findAll(Pageable pageable) {
		return materialDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Material> findByNombre(String term){
		return materialDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}
}
