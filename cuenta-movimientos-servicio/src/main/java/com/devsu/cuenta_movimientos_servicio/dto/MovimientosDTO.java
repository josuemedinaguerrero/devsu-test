package com.devsu.cuenta_movimientos_servicio.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDTO {

  private LocalDate fecha;
  private String tipoMovimiento;
  private Double valor;
  private Double saldo;

}
