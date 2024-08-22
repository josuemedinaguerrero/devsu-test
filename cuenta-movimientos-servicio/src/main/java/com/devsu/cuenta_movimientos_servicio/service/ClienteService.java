package com.devsu.cuenta_movimientos_servicio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsu.cuenta_movimientos_servicio.dto.ClienteDTO;

@Service
public class ClienteService {

  private final RestTemplate restTemplate;
  private final String clienteServiceUrl = "http://localhost:8080/api/clientes";

  public ClienteService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ClienteDTO obtenerCliente(Long clienteId) {
    try {
      String url = clienteServiceUrl + "/" + clienteId;
      return restTemplate.getForObject(url, ClienteDTO.class);
    } catch (Exception e) {
      return null;
    }
  }
}
