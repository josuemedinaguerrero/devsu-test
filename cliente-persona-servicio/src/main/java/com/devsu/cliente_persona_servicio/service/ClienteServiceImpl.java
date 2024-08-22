package com.devsu.cliente_persona_servicio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cliente_persona_servicio.dto.ClienteResponseDTO;
import com.devsu.cliente_persona_servicio.entities.Cliente;
import com.devsu.cliente_persona_servicio.repository.ClienteRepository;
import com.devsu.cliente_persona_servicio.exception.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public ClienteResponseDTO saveCliente(Cliente cliente) {
    clienteRepository.save(cliente);
    return mapToDTO(cliente);
  }

  @Override
  public ClienteResponseDTO getClienteById(Long clienteId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

    return mapToDTO(cliente);
  }

  @Override
  public ClienteResponseDTO updateCliente(Cliente cliente) {
    if (!clienteRepository.existsById(cliente.getPersonaId()))
      throw new ResourceNotFoundException("Cliente no encontrado con id: " + cliente.getPersonaId());

    Cliente updatedCliente = clienteRepository.save(cliente);

    return mapToDTO(updatedCliente);
  }

  @Override
  public void deleteCliente(Long clienteId) {
    if (!clienteRepository.existsById(clienteId))
      throw new ResourceNotFoundException("Cliente no encontrado con id: " + clienteId);

    clienteRepository.deleteById(clienteId);
  }

  @Override
  public List<ClienteResponseDTO> getAllClientes() {
    return clienteRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  private ClienteResponseDTO mapToDTO(Cliente cliente) {
    return ClienteResponseDTO.builder()
        .personaId(cliente.getPersonaId())
        .nombre(cliente.getNombre())
        .edad(cliente.getEdad())
        .identificacion(cliente.getIdentificacion())
        .direccion(cliente.getDireccion())
        .telefono(cliente.getTelefono())
        .build();
  }

}
