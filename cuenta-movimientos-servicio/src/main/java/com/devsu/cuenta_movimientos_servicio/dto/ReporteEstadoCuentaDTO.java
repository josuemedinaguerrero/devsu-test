package com.devsu.cuenta_movimientos_servicio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadoCuentaDTO {

  private List<CuentaDTO> cuentas;
  private List<MovimientosDTO> movimientos;
  private ClienteDTO cliente;

}
