package com.g5.app.models.service;

import com.g5.app.models.dao.IItemInventarioDao;
import com.g5.app.models.entity.ItemInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemInventarioServiceImpl implements IItemInventarioService{

    @Autowired
    private IItemInventarioDao itemInventarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<ItemInventario> findAll() {
        return itemInventarioDao.findAll();
    }

    @Override
    public void save(ItemInventario itemInventario) {
        itemInventarioDao.save(itemInventario);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInventario findOne(Long id) {
        return itemInventarioDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        itemInventarioDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<ItemInventario> findByNombreAndInventarioId(Long id, String term){
        return itemInventarioDao.findByInventario_IdAndMaterial_NombreContainingIgnoreCase(id, term);
    }

    @Override
    @Transactional(readOnly=true)
    public ItemInventario findByInventario_IdAndMaterial_Id(Long id,Long id2){
        return itemInventarioDao.findByInventario_IdAndMaterial_Id(id, id2);
    }
}
