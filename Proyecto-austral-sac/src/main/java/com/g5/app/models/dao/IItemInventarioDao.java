package com.g5.app.models.dao;

import com.g5.app.models.entity.ItemInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemInventarioDao extends JpaRepository<ItemInventario,Long> {

    public List<ItemInventario> findByInventario_IdAndMaterial_NombreContainingIgnoreCase(Long  id, String term);

    public ItemInventario findByInventario_IdAndMaterial_Id(Long  id, Long id2);
}
