package com.remises.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VIAJE")
public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "ID_CHOFER")
	private Chofer chofer;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@NotNull
	@DecimalMin("0.0")
	@Digits(integer = 14, fraction = 2)
	private Double precio;
	
	@NotNull
	@Size(max = 50)
	private String origen;
	
	@Size(max = 20)
	@Column(name = "LATITUD_OR")
	private String latitudOrigen;
	
	@Size(max = 20)
	@Column(name = "LONGITUD_OR")
	private String longitudOrigen;
		
	@NotNull
	@Size(max = 50)
	private String destino;
	
	@Size(max = 20)
	@Column(name = "LATITUD_DE")
	private String latitudDestino;
	
	@Size(max = 20)
	@Column(name = "LONGITUD_DE")
	private String longitudDestino;

//	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;

//	@NotNull
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Time hora;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getLatitudOrigen() {
		return latitudOrigen;
	}

	public void setLatitudOrigen(String latitudOrigen) {
		this.latitudOrigen = latitudOrigen;
	}

	public String getLongitudOrigen() {
		return longitudOrigen;
	}

	public void setLongitudOrigen(String longitudOrigen) {
		this.longitudOrigen = longitudOrigen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getLatitudDestino() {
		return latitudDestino;
	}

	public void setLatitudDestino(String latitudDestino) {
		this.latitudDestino = latitudDestino;
	}

	public String getLongitudDestino() {
		return longitudDestino;
	}

	public void setLongitudDestino(String longitudDestino) {
		this.longitudDestino = longitudDestino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

}
