package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.ITipoDao;
import com.g5.app.models.entity.Tipo;

@Service
public class TipoServiceImpl implements ITipoService{
	
	@Autowired
	private ITipoDao tipoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tipo> findAll(){
		return (List<Tipo>) tipoDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Tipo tipo) {
		tipoDao.save(tipo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Tipo findOne(Long id) {
		return tipoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		tipoDao.deleteById(id);
	}
}
