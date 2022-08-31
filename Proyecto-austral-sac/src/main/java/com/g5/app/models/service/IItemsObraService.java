package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.ItemsObra;

public interface IItemsObraService {

	public List<ItemsObra> findAll();

    public void save (ItemsObra itemsObra);

    public ItemsObra findOne(Long id);

    public void delete(Long id);
}
