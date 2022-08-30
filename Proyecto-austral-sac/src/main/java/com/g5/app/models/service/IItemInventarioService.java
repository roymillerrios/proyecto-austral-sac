package com.g5.app.models.service;

import com.g5.app.models.entity.ItemInventario;

import java.util.List;


public interface IItemInventarioService {

    public List<ItemInventario> findAll();

    public void save (ItemInventario itemInventario);

    public ItemInventario findOne(Long id);

    public void delete(Long id);

    public List<ItemInventario> findByNombreAndInventarioId(Long id,String term);

	public ItemInventario findByInventario_IdAndMaterial_Id(Long id,Long id2);
}
