package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.IObraDao;
import com.g5.app.models.entity.Obra;

@Service
public class ObraServiceImpl implements IObraService{
	
	@Autowired
	private IObraDao obraDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Obra> findAll(){
		return (List<Obra>) obraDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Obra obra) {
		obraDao.save(obra);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Obra findOne(Long id) {
		return obraDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		obraDao.deleteById(id);
	}
}
