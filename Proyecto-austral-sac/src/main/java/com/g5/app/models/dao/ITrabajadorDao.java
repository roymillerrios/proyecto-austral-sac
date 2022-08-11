package com.g5.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.Trabajador;

@Repository
public interface ITrabajadorDao extends PagingAndSortingRepository<Trabajador, Long>{

}
