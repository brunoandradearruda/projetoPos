package com.mballem.curso.boot.service;
import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.model.Ferias;
import com.mballem.curso.boot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeriasService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Ferias calcularFerias(Long funcionarioId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        // Aqui você implementaria a lógica de cálculo das férias baseada na data de entrada do funcionário.
        // Esta é uma simplificação.
        LocalDate inicioFerias = LocalDate.now();
        LocalDate fimFerias = inicioFerias.plusDays(30);
        int totalDias = 30;

        return new Ferias(inicioFerias, fimFerias, totalDias);
    }
}
