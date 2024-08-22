package com.devsu.cliente_persona_servicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cliente_persona_servicio.entities.Cliente;
import com.devsu.cliente_persona_servicio.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
    return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
    cliente.setPersonaId(id);
    return new ResponseEntity<>(clienteService.updateCliente(cliente), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
    clienteService.deleteCliente(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> getAllClientes() {
    return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
  }

}
