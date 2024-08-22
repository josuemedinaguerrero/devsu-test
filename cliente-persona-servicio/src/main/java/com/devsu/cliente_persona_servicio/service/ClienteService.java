package com.devsu.cliente_persona_servicio.service;

import java.util.List;

import com.devsu.cliente_persona_servicio.dto.ClienteResponseDTO;
import com.devsu.cliente_persona_servicio.entities.Cliente;

public interface ClienteService {

  ClienteResponseDTO saveCliente(Cliente cliente);

  ClienteResponseDTO getClienteById(Long clienteId);

  ClienteResponseDTO updateCliente(Cliente cliente);

  void deleteCliente(Long clienteId);

  List<ClienteResponseDTO> getAllClientes();

}
