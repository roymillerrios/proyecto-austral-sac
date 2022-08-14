package com.g5.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.g5.app.models.entity.Trabajador;

@Repository
public interface ITrabajadorDao extends PagingAndSortingRepository<Trabajador, Long>,JpaRepository<Trabajador, Long>{
	
	@Query("select t from Trabajador t where t.DNI like %?1%")
	public List<Trabajador> findByDNI(String term);

	public List<Trabajador> findByDNILikeIgnoreCase(String term);
	
	public Trabajador findByEmail(String email);
}
