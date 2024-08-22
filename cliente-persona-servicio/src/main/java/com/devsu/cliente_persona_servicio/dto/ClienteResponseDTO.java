package com.devsu.cliente_persona_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

  private Long personaId;
  private String nombre;
  private int edad;
  private String identificacion;
  private String direccion;
  private String telefono;

}
