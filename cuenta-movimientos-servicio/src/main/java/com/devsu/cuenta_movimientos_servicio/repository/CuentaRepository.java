package com.devsu.cuenta_movimientos_servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

  List<Cuenta> findByClienteId(Long clienteId);

}
