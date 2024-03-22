package com.projetouposunipe.repository;


import com.projetouposunipe.model.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FeriasRepository extends JpaRepository<Ferias, Long> {
    List<Ferias> findByFuncionarioIdAndInicioFeriasBetween(Long funcionarioId, LocalDate inicioPeriodo, LocalDate fimPeriodo);

}
