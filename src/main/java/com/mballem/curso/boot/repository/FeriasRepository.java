package com.mballem.curso.boot.repository;


import com.mballem.curso.boot.model.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeriasRepository extends JpaRepository<Ferias, Long> {
}
