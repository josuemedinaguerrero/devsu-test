package com.devsu.cuenta_movimientos_servicio.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimientos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long movimientosId;

  private LocalDate fecha;
  private String tipoMovimiento;
  private Double valor;
  private Double saldo;

  @ManyToOne
  @JoinColumn(name = "numero_cuenta")
  private Cuenta cuenta;

}
