package com.remises.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remises.model.Viaje;
import com.remises.repository.ViajeRepository;
import com.remises.response.ViajeResponse;

@Service
public class ViajeService {

	@Autowired
    private ViajeRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(ViajeService.class);
	
	public ViajeResponse getViaje(Long id) {
		LOGGER.info("Obteniendo Viaje");
		
		Viaje viaje = this.repository.findOne(id);
		ViajeResponse res = new ViajeResponse();
		
		res.setChofer(viaje.getChofer() == null ? null : viaje.getChofer().getId());
		res.setCliente(viaje.getCliente() == null ? null : viaje.getCliente().getId());
		res.setDestino(viaje.getDestino());
		res.setFecha(viaje.getFecha());
		res.setHora(viaje.getHora());
		res.setId(viaje.getId());
		res.setLatitudDestino(viaje.getLatitudDestino());
		res.setLatitudOrigen(viaje.getLatitudOrigen());
		res.setLongitudDestino(viaje.getLongitudDestino());
		res.setLongitudOrigen(viaje.getLongitudOrigen());
		res.setOrigen(viaje.getOrigen());
		res.setPrecio(viaje.getPrecio());
		res.setEstado(viaje.getEstado());
		
		return res;
	}
	
	public List<ViajeResponse> listarViajes() {
		LOGGER.info("Obteniendo todos los viajes");
		
		List<ViajeResponse> response = new ArrayList<ViajeResponse>();
		List<Viaje> viajes = (List<Viaje>) this.repository.findAll();
		
		for (Viaje viaje : viajes) {
			ViajeResponse res = new ViajeResponse();

			res.setChofer(viaje.getChofer() == null ? null : viaje.getChofer().getId());
			res.setCliente(viaje.getCliente() == null ? null : viaje.getCliente().getId());
			res.setDestino(viaje.getDestino());
			res.setFecha(viaje.getFecha());
			res.setHora(viaje.getHora());
			res.setId(viaje.getId());
			res.setLatitudDestino(viaje.getLatitudDestino());
			res.setLatitudOrigen(viaje.getLatitudOrigen());
			res.setLongitudDestino(viaje.getLongitudDestino());
			res.setLongitudOrigen(viaje.getLongitudOrigen());
			res.setOrigen(viaje.getOrigen());
			res.setPrecio(viaje.getPrecio());
			res.setEstado(viaje.getEstado());
			
			response.add(res);
		}		
		return response;
	}
	
}

