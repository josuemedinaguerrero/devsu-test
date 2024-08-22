package com.devsu.cuenta_movimientos_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

  private Long personaId;
  private String nombre;
  private int edad;
  private String identificacion;
  private String direccion;
  private String telefono;

}
