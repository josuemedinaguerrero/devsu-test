package com.devsu.cuenta_movimientos_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

  private Long numeroCuenta;
  private String tipoCuenta;
  private Double saldoInicial;

}
