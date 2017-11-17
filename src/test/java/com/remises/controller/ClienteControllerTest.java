package com.remises.controller;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.remises.global.Constantes;
import com.remises.model.Cliente;

public class ClienteControllerTest {

	private static final Logger LOGGER = Logger.getLogger(ClienteController.class);

    /* GET */
	@Test
    @SuppressWarnings("unchecked")
	private static void listAllClientes() {
        LOGGER.info("Testing listAllClientes API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> clientesMap = restTemplate.getForObject(Constantes.URI + "cliente", List.class);
         
        if (clientesMap != null){
            for (LinkedHashMap<String, Object> map : clientesMap) {
                LOGGER.info("Cliente : id=" + map.get("id")
                	    + ", Viajes=" + map.get("viajes")
                	    + ", Nombre=" + map.get("nombre")
                	    + ", Apellido=" + map.get("apellido")
                	    + ", DNI=" + map.get("dni")
                	    + ", Telefono=" + map.get("telefono")
                	    + ", Email=" + map.get("email")
                	    + ", Direccion=" + map.get("direccion")
                	    + ", Codigo Postal=" + map.get("codigoPostal")
                	    + ", Ciudad=" + map.get("ciudad")
                	    + ", Latitud=" + map.get("latitud")
                	    + ", Longitud=" + map.get("longitud"));
            }
        }else{
            LOGGER.warn("NO EXISTEN Clientes.......");
        }
    }
     
    /* GET */
	@Test
    private static void getCliente() {
        LOGGER.info("Testing getCliente API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cliente cliente = restTemplate.getForObject(Constantes.URI+"cliente/1", Cliente.class);
        LOGGER.info(cliente);
    }

    /* POST */
	@Test
    private static void createCliente() {
        LOGGER.info("Testing createCliente API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cliente cliente = new Cliente();
        cliente.setApellido("Martinez");
        cliente.setCiudad(1);
        cliente.setCodigoPostal(1759);
        cliente.setDireccion("Dragones 2505");
        cliente.setDni(34235783);
        cliente.setEmail("gustavo-9189@hotmail.com");
        cliente.setLatitud("123314234");
        cliente.setLongitud("324234233");
        cliente.setNombre("Gustavo Alfredo");
        cliente.setTelefono("2342341123");
        
        URI uri = restTemplate.postForLocation(Constantes.URI + "cliente", cliente, Cliente.class);
        LOGGER.info("Location : " + uri.toASCIIString());
    }
 
    /* PUT */
	@Test
    private static void updateCliente() {
        LOGGER.info("Testing updateCliente API----------");
        RestTemplate restTemplate = new RestTemplate();
        Cliente cliente  = new Cliente();
        cliente.setApellido("apellidoActualizado");
        cliente.setCiudad(2);
        cliente.setCodigoPostal(1111);
        cliente.setDireccion("direccionActualizada");
        cliente.setDni(40111222);
        cliente.setEmail("email@actualizado.com");
        cliente.setId(1L);
        cliente.setLatitud("latitudActualizada");
        cliente.setLongitud("longitudActualizada");
        cliente.setNombre("nombreActualizado");
        cliente.setTelefono("telefonoActualizado");

        restTemplate.put(Constantes.URI + "cliente/1", cliente);
        LOGGER.info(cliente);
    }
 
    /* DELETE */
	@Test
    private static void deleteCliente() {
        LOGGER.info("Testing deleteCliente API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Constantes.URI + "cliente/1");
    }
 
 
    /* DELETE */
	@Test
    private static void deleteAllClientes() {
        LOGGER.info("Testing all deleteAllClientes API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Constantes.URI + "cliente");
    }
 
    // Ejecuta todas las pruebas
    public static void main(String args[]){
        listAllClientes();
        getCliente();
        createCliente();
        listAllClientes();
        updateCliente();
        listAllClientes();
        deleteCliente();
        createCliente();
        listAllClientes();
        deleteAllClientes();
        listAllClientes();
    }

}