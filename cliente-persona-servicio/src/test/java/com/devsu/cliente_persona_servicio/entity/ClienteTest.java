package com.devsu.cliente_persona_servicio.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.devsu.cliente_persona_servicio.entities.Cliente;

public class ClienteTest {

  @Test
  void ClienteConstructorGetters() {
    Cliente cliente = new Cliente("123456", "Activo");

    Assertions.assertThat(cliente.getPassword()).isEqualTo("123456");
    Assertions.assertThat(cliente.getEstado()).isEqualTo("Activo");
  }

  @Test
  void ClienteSetters() {
    Cliente cliente = new Cliente();
    cliente.setPassword("123456");
    cliente.setEstado("Activo");

    Assertions.assertThat(cliente.getPassword()).isEqualTo("123456");
    Assertions.assertThat(cliente.getEstado()).isEqualTo("Activo");
  }

  @Test
  void ClienteEqualsAndHashCode() {
    Cliente cliente1 = new Cliente("123456", "Activo");
    Cliente cliente2 = new Cliente("123456", "Activo");

    Assertions.assertThat(cliente1).isEqualTo(cliente2);
    Assertions.assertThat(cliente2.hashCode()).isEqualTo(cliente2.hashCode());
  }

  @Test
  void ClienteInheritance() {
    Cliente cliente = new Cliente();
    cliente.setNombre("Josue");
    cliente.setEdad(21);

    Assertions.assertThat(cliente.getNombre()).isEqualTo("Josue");
    Assertions.assertThat(cliente.getEdad()).isEqualTo(21);
  }

}
