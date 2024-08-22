package com.devsu.cliente_persona_servicio.service;

import java.util.List;

import com.devsu.cliente_persona_servicio.entities.Cliente;

public interface ClienteService {

  Cliente saveCliente(Cliente cliente);

  Cliente getClienteById(Long clienteId);

  Cliente updateCliente(Cliente cliente);

  void deleteCliente(Long clienteId);

  List<Cliente> getAllClientes();

}
