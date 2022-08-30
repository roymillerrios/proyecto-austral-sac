package com.g5.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.Material;

@Repository
public interface IMaterialDao extends JpaRepository<Material, Long>{

	@Query("select m from Material m where m.nombre like %?1%")
	public List<Material> findByNombre(String term);

	public List<Material> findByNombreLikeIgnoreCase(String term);

	public List<Material> findByItemsInventario_Inventario_IdAndNombreContainingIgnoreCase(Long  id, String term);
	

	
}
