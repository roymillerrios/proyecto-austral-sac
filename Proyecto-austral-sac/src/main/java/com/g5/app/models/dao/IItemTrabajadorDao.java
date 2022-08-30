package com.g5.app.models.dao;

import com.g5.app.models.entity.ItemTrabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemTrabajadorDao extends JpaRepository<ItemTrabajador,Long> {
    /*public ItemTrabajador findByInventario_IdAndMaterial_Id(Long  id, Long id2);*/
}
