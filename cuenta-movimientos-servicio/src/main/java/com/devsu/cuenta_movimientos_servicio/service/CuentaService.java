package com.devsu.cuenta_movimientos_servicio.service;

import java.util.List;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;

public interface CuentaService {

  Cuenta saveCuenta(Cuenta cuenta);

  Cuenta getCuentaById(Long cuentaId);

  Cuenta updateCuenta(Cuenta cuenta);

  void deleteCuenta(Long cuentaId);

  List<Cuenta> getAllCuentas();

}
