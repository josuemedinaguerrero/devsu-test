package com.devsu.cuenta_movimientos_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;
import com.devsu.cuenta_movimientos_servicio.entities.Movimientos;
import com.devsu.cuenta_movimientos_servicio.exception.InsufficientFundsException;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceCreationException;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceNotFoundException;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceUpdateException;
import com.devsu.cuenta_movimientos_servicio.repository.CuentaRepository;
import com.devsu.cuenta_movimientos_servicio.repository.MovimientosRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimientosServiceImpl implements MovimientosService {

  @Autowired
  private MovimientosRepository movimientosRepository;

  @Autowired
  private CuentaRepository cuentaRepository;

  @Override
  @Transactional
  public Movimientos saveMovimiento(Movimientos movimiento) {
    Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getNumeroCuenta())
        .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

    if (movimiento.getValor() > cuenta.getSaldoInicial())
      throw new InsufficientFundsException("Saldo no disponible");

    // Actualizar saldo de la cuenta
    double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
    cuenta.setSaldoInicial(nuevoSaldo);
    cuentaRepository.save(cuenta);

    movimiento.setSaldo(nuevoSaldo);

    return movimientosRepository.save(movimiento);
  }

  @Override
  public Movimientos getMovimientoById(Long numeroCuenta) {
    return movimientosRepository.findById(numeroCuenta)
        .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
  }

  @Override
  public Movimientos updateMovimiento(Movimientos movimientos) {
    try {
      if (!movimientosRepository.existsById(movimientos.getMovimientosId()))
        throw new ResourceNotFoundException("Movimiento no encontrado con id: " + movimientos.getMovimientosId());

      return movimientosRepository.save(movimientos);
    } catch (Exception e) {
      throw new ResourceUpdateException("Error al actualizar el movimientos");
    }
  }

  @Override
  public void deleteMovimiento(Long numeroCuenta) {
    if (!movimientosRepository.existsById(numeroCuenta))
      throw new ResourceNotFoundException("Movimiento no encontrado con id: " + numeroCuenta);

    movimientosRepository.deleteById(numeroCuenta);
  }

  @Override
  public List<Movimientos> getAllMovimientos() {
    return movimientosRepository.findAll();
  }

}
