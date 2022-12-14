package com.g5.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.g5.app.models.entity.Material;

public interface IMaterialService {

	public List<Material> findAll();
	
	public Page<Material> findAll(Pageable pageable);

	public void save (Material material);
	
	public Material findOne(Long id);
	
	public void delete(Long id);
	
	public List<Material> findByNombre(String term);
	
	public List<Material> findByNombreInventario(Long id, String term);
}
