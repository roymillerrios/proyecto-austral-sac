package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.Inventario;

public interface IInventarioService {
	
	public List<Inventario> findAll();

	public void save (Inventario inventario);
	
	public Inventario findOne(Long id);
	
	public void delete(Long id);

}
