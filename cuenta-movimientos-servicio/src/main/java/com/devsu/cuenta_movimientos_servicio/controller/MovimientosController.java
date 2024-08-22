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

import com.devsu.cuenta_movimientos_servicio.entities.Movimientos;
import com.devsu.cuenta_movimientos_servicio.service.MovimientosService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientosController {

  @Autowired
  private MovimientosService movimientosService;

  @PostMapping
  public ResponseEntity<Movimientos> createMovimiento(@RequestBody Movimientos movimiento) {
    return new ResponseEntity<>(movimientosService.saveMovimiento(movimiento), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movimientos> getMovimientoById(@PathVariable Long id) {
    return new ResponseEntity<>(movimientosService.getMovimientoById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movimientos> updateMovimiento(@PathVariable Long id, @RequestBody Movimientos movimiento) {
    movimiento.setMovimientosId(id);
    return new ResponseEntity<>(movimientosService.updateMovimiento(movimiento), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
    movimientosService.deleteMovimiento(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<Movimientos>> getAllMovimientos() {
    return new ResponseEntity<>(movimientosService.getAllMovimientos(), HttpStatus.OK);
  }

}
