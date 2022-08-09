package com.g5.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
}
