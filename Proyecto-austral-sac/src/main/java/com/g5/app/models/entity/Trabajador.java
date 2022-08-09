package com.g5.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="trabajador")
public class Trabajador {

	
	@Id
	@Column(name="idTrabajador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idTrabajador", nullable = false)
    private Usuario usuario;
	
	@NotEmpty
	@Column(name = "DNI")
	private String DNI;
	
	@NotNull
	@Column(name = "FechaIngreso")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date FechaIngreso;
	
	@NotEmpty
	@Column(name = "NombreCompleto")
	private String NombreCompleto;
	
	@NotEmpty
	@Column(name = "ApellidoMaterno")
	private String ApellidoMaterno;
	
	@NotEmpty
	@Column(name = "ApellidoPaterno")
	private String ApellidoPaterno;
	
	@NotEmpty
	@Column (name = "ExamenSeguridad")
	private String ExamenSeguridad;
	
	@Lob
	@Column(name = "SCTRPension")
	private byte[] SCTRPension;
	
	@Lob
	@Column(name = "SCTRSalud")
	private byte[] SCTRSalud;
	
	@Lob
	@Column(name = "AntPenales")
	private byte[] AntPenales;
	
	@Lob
	@Column(name = "AntPoliciales")
	private byte[] AntPoliciales;
	
	@Lob
	@Column(name = "CertDomiciliario")
	private byte[] CertDomiciliario;
	
}