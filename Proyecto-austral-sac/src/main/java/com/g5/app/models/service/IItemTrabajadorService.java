package com.g5.app.models.service;

import com.g5.app.models.entity.ItemTrabajador;

import java.util.List;

public interface IItemTrabajadorService {
    public List<ItemTrabajador> findAll();

    public void save (ItemTrabajador itemTrabajador);

    public ItemTrabajador findOne(Long id);

    public void delete(Long id);

    /*public ItemTrabajador findByInventarioAndMaterial(Long id, Long id2);*/
}
