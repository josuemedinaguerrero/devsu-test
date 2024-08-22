package com.devsu.cuenta_movimientos_servicio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta_movimientos_servicio.dto.ClienteDTO;
import com.devsu.cuenta_movimientos_servicio.dto.CuentaDTO;
import com.devsu.cuenta_movimientos_servicio.dto.MovimientosDTO;
import com.devsu.cuenta_movimientos_servicio.dto.ReporteEstadoCuentaDTO;
import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;
import com.devsu.cuenta_movimientos_servicio.entities.Movimientos;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceNotFoundException;
import com.devsu.cuenta_movimientos_servicio.repository.CuentaRepository;
import com.devsu.cuenta_movimientos_servicio.repository.MovimientosRepository;

@Service
public class ReporteServiceImpl implements ReporteService {

  @Autowired
  private CuentaRepository cuentaRepository;

  @Autowired
  private MovimientosRepository movimientosRepository;

  @Autowired
  private ClienteService clienteService;

  @Override
  public ReporteEstadoCuentaDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
    ClienteDTO clienteDTO = clienteService.obtenerCliente(clienteId);

    if (clienteDTO == null)
      throw new ResourceNotFoundException("Cliente no encontrado con id " + clienteId);

    List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
    if (cuentas.isEmpty())
      throw new ResourceNotFoundException("No se han encontrado cuentas registradas");

    List<Movimientos> movimientos = movimientosRepository.findByFechaBetweenAndCuentaClienteId(fechaInicio, fechaFin,
        clienteId);

    List<CuentaDTO> cuentasDTO = cuentas.stream()
        .map(cuenta -> new CuentaDTO(
            cuenta.getNumeroCuenta(),
            cuenta.getTipoCuenta(),
            cuenta.getSaldoInicial()))
        .collect(Collectors.toList());

    List<MovimientosDTO> movimientosDTO = movimientos.stream()
        .map(movimiento -> new MovimientosDTO(
            movimiento.getFecha(),
            movimiento.getTipoMovimiento(),
            movimiento.getValor(),
            movimiento.getSaldo()))
        .collect(Collectors.toList());

    return new ReporteEstadoCuentaDTO(cuentasDTO, movimientosDTO, clienteDTO);
  }

}
