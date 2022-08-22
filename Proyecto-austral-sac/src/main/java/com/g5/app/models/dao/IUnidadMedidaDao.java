package com.g5.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.UnidadMedida;

@Repository
public interface IUnidadMedidaDao extends JpaRepository<UnidadMedida, Long>{

}
