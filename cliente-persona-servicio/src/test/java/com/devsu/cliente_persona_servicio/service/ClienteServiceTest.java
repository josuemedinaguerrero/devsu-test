package com.devsu.cliente_persona_servicio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devsu.cliente_persona_servicio.entities.Cliente;
import com.devsu.cliente_persona_servicio.exception.ResourceNotFoundException;
import com.devsu.cliente_persona_servicio.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

  @Mock
  private ClienteRepository clienteRepository;

  @InjectMocks
  private ClienteServiceImpl clienteService;

  private Cliente cliente;

  @BeforeEach
  void initial() {
    cliente = new Cliente();
    cliente.setPersonaId(1L);
    cliente.setNombre("Josue");
    cliente.setEstado("Activo");
    cliente.setPassword("123456");
  }

  @Test
  void ClienteServiceFindAllClientes() {
    when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

    List<Cliente> clientes = clienteService.getAllClientes();

    Assertions.assertThat(clientes).isNotNull();
  }

  @Test
  void ClienteServiceSaveCliente() {
    when(clienteRepository.save(cliente)).thenReturn(cliente);

    Cliente savedCliente = clienteService.saveCliente(cliente);

    assertEquals(cliente, savedCliente);
    verify(clienteRepository, times(1)).save(cliente);
  }

  @Test
  void ClienteServiceGetClienteById_ClienteExiste() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

    Cliente foundCliente = clienteService.getClienteById(1L);

    assertEquals(cliente, foundCliente);
    verify(clienteRepository, times(1)).findById(1L);
  }

  @Test
  void ClienteServiceGetClienteById_ClienteNoExiste() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      clienteService.getClienteById(1L);
    });

    verify(clienteRepository, times(1)).findById(1L);
  }

  @Test
  void ClienteServiceUpdateCliente_ClienteExiste() {
    when(clienteRepository.existsById(1L)).thenReturn(true);
    when(clienteRepository.save(cliente)).thenReturn(cliente);

    Cliente updatedCliente = clienteService.updateCliente(cliente);

    assertEquals(cliente, updatedCliente);
    verify(clienteRepository, times(1)).save(cliente);
  }

  @Test
  void ClienteServiceUpdateCliente_ClienteNoExiste() {
    when(clienteRepository.existsById(1L)).thenReturn(false);

    assertThrows(ResourceNotFoundException.class, () -> {
      clienteService.updateCliente(cliente);
    });

    verify(clienteRepository, times(1)).existsById(1L);
    verify(clienteRepository, times(0)).save(cliente);
  }

  @Test
  void ClienteServiceDeleteCliente_ClienteExiste() {
    when(clienteRepository.existsById(1L)).thenReturn(true);

    clienteService.deleteCliente(1L);

    verify(clienteRepository, times(1)).existsById(1L);
    verify(clienteRepository, times(1)).deleteById(1L);
  }

  @Test
  void ClienteServiceDeleteCliente_ClienteNoExiste() {
    when(clienteRepository.existsById(1L)).thenReturn(false);

    assertThrows(ResourceNotFoundException.class, () -> {
      clienteService.deleteCliente(1L);
    });

    verify(clienteRepository, times(1)).existsById(1L);
    verify(clienteRepository, times(0)).deleteById(1L);
  }

}
