package com.remises.response;

import java.util.Calendar;

public class ViajeResponse {

	private Long id;
	private Long chofer;
	private Long cliente;
	private Double precio;
	private String origen;
	private String latitudOrigen;
	private String longitudOrigen;
	private String destino;
	private String latitudDestino;
	private String longitudDestino;
	private Calendar fecha;
	private String hora;
	private Integer estado;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getChofer() {
		return chofer;
	}
	public void setChofer(Long chofer) {
		this.chofer = chofer;
	}
	public Long getCliente() {
		return cliente;
	}
	public void setCliente(Long cliente) {
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
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
