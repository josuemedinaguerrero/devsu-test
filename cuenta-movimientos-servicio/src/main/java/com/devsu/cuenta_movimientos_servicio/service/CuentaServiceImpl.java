package com.devsu.cuenta_movimientos_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta_movimientos_servicio.dto.ClienteDTO;
import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;
import com.devsu.cuenta_movimientos_servicio.exception.ResourceNotFoundException;
import com.devsu.cuenta_movimientos_servicio.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

  @Autowired
  private CuentaRepository cuentaRepository;

  @Autowired
  private ClienteService clienteService;

  @Override
  public Cuenta saveCuenta(Cuenta cuenta) {
    ClienteDTO cliente = clienteService.obtenerCliente(cuenta.getClienteId());
    if (cliente == null)
      throw new ResourceNotFoundException("Cliente no encontrado con id: " + cuenta.getClienteId());

    return cuentaRepository.save(cuenta);
  }

  @Override
  public Cuenta getCuentaById(Long numeroCuenta) {
    return cuentaRepository.findById(numeroCuenta)
        .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
  }

  @Override
  public Cuenta updateCuenta(Cuenta cuenta) {
    Cuenta cuentaDB = cuentaRepository.findById(cuenta.getNumeroCuenta())
        .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

    if (cuentaDB == null)
      throw new ResourceNotFoundException("Cuenta no encontrada");

    ClienteDTO cliente = clienteService.obtenerCliente(cuenta.getClienteId());
    if (cliente == null)
      throw new ResourceNotFoundException("Cliente no encontrado con id: " + cuenta.getClienteId());

    cuenta.setNumeroCuenta(cuentaDB.getNumeroCuenta());
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
