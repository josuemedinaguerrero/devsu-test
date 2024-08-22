package com.devsu.cuenta_movimientos_servicio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta_movimientos_servicio.dto.ReporteEstadoCuentaDTO;
import com.devsu.cuenta_movimientos_servicio.service.ReporteService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

  @Autowired
  private ReporteService reporteService;

  @GetMapping
  public ResponseEntity<ReporteEstadoCuentaDTO> getReporteEstadoCuenta(
      @RequestParam("fechaInicio") LocalDate fechaInicio,
      @RequestParam("fechaFin") LocalDate fechaFin,
      @RequestParam("clienteId") Long clienteId) {
    ReporteEstadoCuentaDTO reporte = reporteService.generarReporte(fechaInicio, fechaFin, clienteId);
    return new ResponseEntity<>(reporte, HttpStatus.OK);
  }

}
