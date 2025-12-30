package com.example.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.supermercado.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

}
