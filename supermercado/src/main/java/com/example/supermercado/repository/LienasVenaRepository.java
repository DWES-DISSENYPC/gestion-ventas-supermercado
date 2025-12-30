package com.example.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.supermercado.model.LineasVenta;

@Repository
public interface LienasVenaRepository extends JpaRepository<LineasVenta, Long> {

}
