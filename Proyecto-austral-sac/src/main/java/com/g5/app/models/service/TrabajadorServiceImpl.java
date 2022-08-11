package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.entity.Trabajador;
import com.g5.app.models.dao.ITrabajadorDao;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

	@Autowired
	private ITrabajadorDao trabajadorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
		// TODO Auto-generated method stub
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Trabajador trabajador) {
		trabajadorDao.save(trabajador);

	}

	@Override
	@Transactional(readOnly = true)
	public Trabajador findOne(Long id) {
		// TODO Auto-generated method stub
		return trabajadorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		trabajadorDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<Trabajador> findAll(Pageable pageable) {
		return trabajadorDao.findAll(pageable);
	}
}
