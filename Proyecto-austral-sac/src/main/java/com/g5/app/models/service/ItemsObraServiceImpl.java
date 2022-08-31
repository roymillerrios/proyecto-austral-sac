package com.g5.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g5.app.models.dao.IItemsObraDao;
import com.g5.app.models.entity.ItemsObra;

@Service
public class ItemsObraServiceImpl implements IItemsObraService{

	@Autowired
	private IItemsObraDao itemsObraDao;
	
	@Override
	public List<ItemsObra> findAll() {
		return itemsObraDao.findAll();
	}

	@Override
	public void save(ItemsObra itemsObra) {
		itemsObraDao.save(itemsObra);
		
	}

	@Override
	public ItemsObra findOne(Long id) {
		return itemsObraDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		itemsObraDao.deleteById(id);
		
	}

	
}
