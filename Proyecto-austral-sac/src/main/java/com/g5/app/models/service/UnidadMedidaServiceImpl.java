package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.IUnidadMedidaDao;
import com.g5.app.models.entity.UnidadMedida;

public class UnidadMedidaServiceImpl implements IUnidadMedidaService{
	
	@Autowired
	private IUnidadMedidaDao unidadmedidaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<UnidadMedida> findAll(){
		return (List<UnidadMedida>) unidadmedidaDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(UnidadMedida unidadmedida) {
		unidadmedidaDao.save(unidadmedida);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UnidadMedida findOne(Long id) {
		return unidadmedidaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		unidadmedidaDao.deleteById(id);
	}
}
