package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.IInventarioDao;
import com.g5.app.models.entity.Inventario;

public class InventarioServiceImpl implements IInventarioService{
	
	@Autowired
	private IInventarioDao inventarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Inventario> findAll(){
		return (List<Inventario>) inventarioDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Inventario inventario) {
		inventarioDao.save(inventario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Inventario findOne(Long id) {
		return inventarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		inventarioDao.deleteById(id);
	}

}
