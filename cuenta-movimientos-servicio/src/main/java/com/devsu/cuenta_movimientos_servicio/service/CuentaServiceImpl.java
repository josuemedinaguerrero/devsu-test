package com.devsu.cuenta_movimientos_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceCreationException;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceNotFoundException;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceUpdateException;
import com.devsu.cuenta_movimientos_servicio.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

  @Autowired
  private CuentaRepository cuentaRepository;

  @Override
  public Cuenta saveCuenta(Cuenta cuenta) {
    return cuentaRepository.save(cuenta);
  }

  @Override
  public Cuenta getCuentaById(Long numeroCuenta) {
    return cuentaRepository.findById(numeroCuenta)
        .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrado"));
  }

  @Override
  public Cuenta updateCuenta(Cuenta cuenta) {
    if (!cuentaRepository.existsById(cuenta.getNumeroCuenta()))
      throw new ResourceNotFoundException("Cuenta no encontrado con id: " + cuenta.getNumeroCuenta());

    return cuentaRepository.save(cuenta);
  }

  @Override
  public void deleteCuenta(Long numeroCuenta) {
    if (!cuentaRepository.existsById(numeroCuenta))
      throw new ResourceNotFoundException("Cuenta no encontrado con id: " + numeroCuenta);

    cuentaRepository.deleteById(numeroCuenta);
  }

  @Override
  public List<Cuenta> getAllCuentas() {
    return cuentaRepository.findAll();
  }

}
