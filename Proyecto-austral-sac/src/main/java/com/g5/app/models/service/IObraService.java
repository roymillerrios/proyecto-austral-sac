package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.Obra;

public interface IObraService {
	
	public List<Obra> findAll();
	
	public void save(Obra obra);
	
	public Obra findOne(Long id);
	
	public void delete(Long id);
	
}
