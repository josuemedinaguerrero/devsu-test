package com.devsu.cuenta_movimientos_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta_movimientos_servicio.entities.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
