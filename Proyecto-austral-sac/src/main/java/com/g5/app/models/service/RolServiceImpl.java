package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.dao.IRolDao;
import com.g5.app.models.entity.Rol;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolDao rolDao;

	@Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return (List<Rol>) rolDao.findAll();
    }

    @Override
    @Transactional
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findOne(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	rolDao.deleteById(id);
    }
}
