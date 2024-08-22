package com.devsu.cuenta_movimientos_servicio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta_movimientos_servicio.entities.Movimientos;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

  List<Movimientos> findByFechaBetweenAndCuentaClienteId(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);

}
