package com.devsu.cliente_persona_servicio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devsu.cliente_persona_servicio.dto.ClienteResponseDTO;
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
  private ClienteResponseDTO clienteResponseDTO;

  @BeforeEach
  void initial() {
    cliente = new Cliente();
    cliente.setPersonaId(1L);
    cliente.setNombre("Josue");
    cliente.setEstado("Activo");
    cliente.setPassword("123456");

    clienteResponseDTO = ClienteResponseDTO.builder()
        .personaId(1L)
        .nombre("Josue")
        .edad(25)
        .identificacion("0987654321")
        .direccion("Avenida Siempre Viva 123")
        .telefono("0987654321")
        .build();
  }

  @Test
  void ClienteServiceFindAllClientes() {
    when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
    List<ClienteResponseDTO> clientes = clienteService.getAllClientes();

    Assertions.assertThat(clientes).isNotNull();
    Assertions.assertThat(clientes.size()).isEqualTo(1);
    Assertions.assertThat(clientes.get(0).getNombre()).isEqualTo("Josue");
  }

  @Test
  void ClienteServiceSaveCliente() {
    when(clienteRepository.save(cliente)).thenReturn(cliente);
    ClienteResponseDTO savedCliente = clienteService.saveCliente(cliente);

    assertEquals(clienteResponseDTO.getNombre(), savedCliente.getNombre());
    verify(clienteRepository, times(1)).save(cliente);
  }

  @Test
  void ClienteServiceGetClienteById_ClienteExiste() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
    ClienteResponseDTO foundCliente = clienteService.getClienteById(1L);

    assertEquals(clienteResponseDTO.getNombre(), foundCliente.getNombre());
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
    ClienteResponseDTO updatedCliente = clienteService.updateCliente(cliente);

    assertEquals(clienteResponseDTO.getNombre(), updatedCliente.getNombre());
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
