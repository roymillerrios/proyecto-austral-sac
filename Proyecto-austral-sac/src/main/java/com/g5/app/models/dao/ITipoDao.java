package com.g5.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.Tipo;

@Repository
public interface ITipoDao extends JpaRepository<Tipo, Long>{

}
