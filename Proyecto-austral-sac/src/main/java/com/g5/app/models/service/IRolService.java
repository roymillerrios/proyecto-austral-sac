package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.Rol;

public interface IRolService {

	public List<Rol> findAll();

    public void save(Rol rol);

    public Rol findOne(Long id);

    public void delete(Long id);

}
