package com.g5.app.models.service;

import com.g5.app.models.dao.IItemTrabajadorDao;
import com.g5.app.models.entity.ItemTrabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IItemTrabajadorServiceImpl implements IItemTrabajadorService{

    @Autowired
    private IItemTrabajadorDao itemTrabajadorDao;

    @Override
    @Transactional(readOnly = true)
    public List<ItemTrabajador> findAll() {
        return itemTrabajadorDao.findAll();
    }

    @Override
    public void save(ItemTrabajador itemTrabajador) {
        itemTrabajadorDao.save(itemTrabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemTrabajador findOne(Long id) {
        return itemTrabajadorDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        itemTrabajadorDao.deleteById(id);
    }

    /*@Override
    public ItemTrabajador findByInventarioAndMaterial(Long id, Long id2) {
        return itemTrabajadorDao.findByInventario_IdAndMaterial_Id(id,id2);
    }*/
}
