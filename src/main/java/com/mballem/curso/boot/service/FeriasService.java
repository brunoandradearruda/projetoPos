package com.mballem.curso.boot.service;

import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.model.Ferias;
import com.mballem.curso.boot.repository.FeriasRepository;
import com.mballem.curso.boot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeriasService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FeriasRepository feriasRepository;

    public Ferias calcularFerias(Long funcionarioId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        LocalDate inicioFerias = LocalDate.now();
        LocalDate fimFerias = inicioFerias.plusDays(30);
        int totalDias = 30;

        Ferias ferias = new Ferias();
        ferias.setInicioFerias(inicioFerias);
        ferias.setFimFerias(fimFerias);
        ferias.setTotalDias(totalDias);
        ferias.setFuncionario(funcionario); // Vincula o funcionário às férias

        // Opcional: salvar as férias no banco de dados aqui, se necessário
        // feriasRepository.save(ferias);

        return ferias;
    }

    public void registrarFerias(Long funcionarioId, LocalDate inicioFerias, int totalDias) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
        LocalDate fimFerias = inicioFerias.plusDays(totalDias);

        Ferias ferias = new Ferias();
        ferias.setInicioFerias(inicioFerias);
        ferias.setFimFerias(fimFerias);
        ferias.setTotalDias(totalDias);
        ferias.setFuncionario(funcionario); // Vincula o funcionário às férias

        feriasRepository.save(ferias); // Salva as férias no banco de dados
    }

    public List<Ferias> buscarTodos() {
        return feriasRepository.findAll();
    }

}
