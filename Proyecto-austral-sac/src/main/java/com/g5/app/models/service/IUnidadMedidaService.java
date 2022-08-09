package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.UnidadMedida;

public interface IUnidadMedidaService {
	
	public List<UnidadMedida> findAll();

	public void save(UnidadMedida unidadmedida);
	
	public UnidadMedida findOne(Long id);
	
	public void delete(Long id);
}
