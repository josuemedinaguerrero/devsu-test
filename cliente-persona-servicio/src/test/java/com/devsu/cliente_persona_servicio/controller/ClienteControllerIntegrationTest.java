package com.devsu.cliente_persona_servicio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.devsu.cliente_persona_servicio.dto.ClienteResponseDTO;
import com.devsu.cliente_persona_servicio.entities.Cliente;
import com.devsu.cliente_persona_servicio.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ClienteService clienteService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void ClienteControllerCreateCliente() throws Exception {
    Cliente cliente = new Cliente();
    cliente.setNombre("Juan Pérez");
    cliente.setEdad(30);
    cliente.setIdentificacion("1234567890");
    cliente.setDireccion("Calle Principal 123");
    cliente.setTelefono("0987654321");
    cliente.setPassword("password123");
    cliente.setEstado("Activo");

    ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.builder()
        .personaId(1L)
        .nombre("Juan Pérez")
        .edad(30)
        .identificacion("1234567890")
        .direccion("Calle Principal 123")
        .telefono("0987654321")
        .build();

    when(clienteService.saveCliente(any(Cliente.class))).thenReturn(clienteResponseDTO);

    mockMvc.perform(post("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cliente)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
        .andExpect(jsonPath("$.edad").value(30))
        .andExpect(jsonPath("$.identificacion").value("1234567890"))
        .andExpect(jsonPath("$.direccion").value("Calle Principal 123"))
        .andExpect(jsonPath("$.telefono").value("0987654321"));

    verify(clienteService, times(1)).saveCliente(any(Cliente.class));
  };

  @Test
  void ClienteControllerGetClienteById() throws Exception {
    Long clienteId = 1L;

    ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.builder()
        .personaId(clienteId)
        .nombre("Josue")
        .edad(25)
        .identificacion("0987654321")
        .direccion("Avenida Siempre Viva 123")
        .telefono("0987654321")
        .build();

    when(clienteService.getClienteById(clienteId)).thenReturn(clienteResponseDTO);

    mockMvc.perform(get("/api/clientes/{id}", clienteId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.personaId").value(clienteId))
        .andExpect(jsonPath("$.nombre").value("Josue"))
        .andExpect(jsonPath("$.edad").value(25))
        .andExpect(jsonPath("$.identificacion").value("0987654321"))
        .andExpect(jsonPath("$.direccion").value("Avenida Siempre Viva 123"))
        .andExpect(jsonPath("$.telefono").value("0987654321"));

    verify(clienteService).getClienteById(clienteId);
  }

  @Test
  public void ClienteControllerUpdateCliente() throws Exception {
    Long clienteId = 1L;
    Cliente cliente = new Cliente();
    cliente.setPersonaId(clienteId);
    cliente.setNombre("Juan Pérez Actualizado");

    ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.builder()
        .personaId(clienteId)
        .nombre("Juan Pérez Actualizado")
        .edad(31)
        .identificacion("0987654321")
        .direccion("Avenida Siempre Viva 123")
        .telefono("0987654321")
        .build();

    when(clienteService.updateCliente(any(Cliente.class))).thenReturn(clienteResponseDTO);

    mockMvc.perform(put("/api/clientes/{id}", clienteId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cliente)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.personaId").value(clienteId))
        .andExpect(jsonPath("$.nombre").value("Juan Pérez Actualizado"))
        .andExpect(jsonPath("$.edad").value(31))
        .andExpect(jsonPath("$.identificacion").value("0987654321"))
        .andExpect(jsonPath("$.direccion").value("Avenida Siempre Viva 123"))
        .andExpect(jsonPath("$.telefono").value("0987654321"));

    verify(clienteService, times(1)).updateCliente(any(Cliente.class));
  }

  @Test
  public void ClienteControllerDeleteCliente() throws Exception {
    Long clienteId = 1L;

    mockMvc.perform(delete("/api/clientes/{id}", clienteId))
        .andExpect(status().isNoContent());

    verify(clienteService, times(1)).deleteCliente(clienteId);
  }

  @Test
  public void ClienteControllerGetAllClientes() throws Exception {
    List<ClienteResponseDTO> clientes = Arrays.asList(
        ClienteResponseDTO.builder().personaId(1L).nombre("Cliente 1").build(),
        ClienteResponseDTO.builder().personaId(2L).nombre("Cliente 2").build());

    when(clienteService.getAllClientes()).thenReturn(clientes);

    mockMvc.perform(get("/api/clientes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].personaId").value(1L))
        .andExpect(jsonPath("$[0].nombre").value("Cliente 1"))
        .andExpect(jsonPath("$[1].personaId").value(2L))
        .andExpect(jsonPath("$[1].nombre").value("Cliente 2"));

    verify(clienteService, times(1)).getAllClientes();
  }

}
