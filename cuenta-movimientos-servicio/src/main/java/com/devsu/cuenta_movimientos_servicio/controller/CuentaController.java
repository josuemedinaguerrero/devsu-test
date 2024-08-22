package com.devsu.cuenta_movimientos_servicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;
import com.devsu.cuenta_movimientos_servicio.service.CuentaService;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

  @Autowired
  private CuentaService cuentaService;

  @PostMapping
  public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
    return new ResponseEntity<>(cuentaService.saveCuenta(cuenta), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
    return new ResponseEntity<>(cuentaService.getCuentaById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
    cuenta.setNumeroCuenta(id);
    return new ResponseEntity<>(cuentaService.updateCuenta(cuenta), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
    cuentaService.deleteCuenta(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<Cuenta>> getAllCuentas() {
    return new ResponseEntity<>(cuentaService.getAllCuentas(), HttpStatus.OK);
  }

}
