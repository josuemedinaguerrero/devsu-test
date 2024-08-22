package com.devsu.cuenta_movimientos_servicio.service;

import java.time.LocalDate;

import com.devsu.cuenta_movimientos_servicio.dto.ReporteEstadoCuentaDTO;

public interface ReporteService {

  ReporteEstadoCuentaDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);

}
