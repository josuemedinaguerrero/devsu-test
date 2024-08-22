package com.devsu.cuenta_movimientos_servicio.service;

import java.util.List;

import com.devsu.cuenta_movimientos_servicio.entities.Movimientos;

public interface MovimientosService {

  Movimientos saveMovimiento(Movimientos movimientos);

  Movimientos getMovimientoById(Long movimientoId);

  Movimientos updateMovimiento(Movimientos movimientos);

  void deleteMovimiento(Long movimientoId);

  List<Movimientos> getAllMovimientos();

}
