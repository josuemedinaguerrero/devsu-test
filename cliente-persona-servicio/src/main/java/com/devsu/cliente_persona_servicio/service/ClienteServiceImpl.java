package com.devsu.cliente_persona_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cliente_persona_servicio.entities.Cliente;
import com.devsu.cliente_persona_servicio.exception.ResourceCreationException;
import com.devsu.cliente_persona_servicio.repository.ClienteRepository;
import com.devsu.cliente_persona_servicio.exception.ResourceNotFoundException;
import com.devsu.cliente_persona_servicio.exception.ResourceUpdateException;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public Cliente saveCliente(Cliente cliente) {
    try {
      return clienteRepository.save(cliente);
    } catch (Exception e) {
      throw new ResourceCreationException("Error al crear el cliente");
    }
  }

  @Override
  public Cliente getClienteById(Long clienteId) {
    return clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
  }

  @Override
  public Cliente updateCliente(Cliente cliente) {
    try {
      if (!clienteRepository.existsById(cliente.getPersonaId()))
        throw new ResourceNotFoundException("Cliente no encontrado con id: " + cliente.getPersonaId());

      return clienteRepository.save(cliente);
    } catch (Exception e) {
      throw new ResourceUpdateException("Error al actualizar el cliente");
    }
  }

  @Override
  public void deleteCliente(Long clienteId) {
    if (!clienteRepository.existsById(clienteId))
      throw new ResourceNotFoundException("Cliente no encontrado con id: " + clienteId);

    clienteRepository.deleteById(clienteId);
  }

  @Override
  public List<Cliente> getAllClientes() {
    return clienteRepository.findAll();
  }

}
