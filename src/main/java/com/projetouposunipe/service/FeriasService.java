package com.projetouposunipe.service;

import com.projetouposunipe.domain.Funcionario;
import com.projetouposunipe.model.Ferias;
import com.projetouposunipe.repository.FeriasRepository;
import com.projetouposunipe.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeriasService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FeriasRepository feriasRepository;

    @Transactional(readOnly = true)
    public List<Ferias> buscarFeriasFuncionarioNoPeriodo(Long funcionarioId, LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        return feriasRepository.findByFuncionarioIdAndInicioFeriasBetween(funcionarioId, inicioPeriodo, fimPeriodo);
    }


    @Transactional
    public boolean registrarFerias(Long funcionarioId, LocalDate inicioFerias, int totalDias) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(
                () -> new IllegalArgumentException("Funcionário não encontrado.")
        );

        if (inicioFerias.isBefore(funcionario.getDataEntrada())) {
            throw new IllegalArgumentException("Não é possível registrar férias para um período anterior à data de entrada do funcionário.");
        }

        if (inicioFerias.isAfter(LocalDate.now().plusMonths(12))) {
            throw new IllegalArgumentException("Não é possível registrar férias para um período superior a doze meses da data atual.");
        }

        if (LocalDate.now().isBefore(funcionario.getDataEntrada().plusMonths(12))) {
            throw new IllegalArgumentException("Funcionário com menos de 12 meses de trabalho não pode gozar férias.");
        }

        LocalDate umAnoAtras = inicioFerias.minusYears(1);
        List<Ferias> feriasUltimos12Meses = buscarFeriasFuncionarioNoPeriodo(funcionarioId, umAnoAtras, inicioFerias);

        int diasJaRegistrados = feriasUltimos12Meses.stream()
                .mapToInt(Ferias::getTotalDias)
                .sum();

        if (diasJaRegistrados + totalDias > 30) {
            throw new IllegalArgumentException("Não é possível registrar mais de 30 dias de férias em um período de 12 meses.");
        }

        Ferias ferias = new Ferias();
        ferias.setInicioFerias(inicioFerias);
        ferias.setFimFerias(inicioFerias.plusDays(totalDias - 1));
        ferias.setTotalDias(totalDias);
        ferias.setFuncionario(funcionario);
        feriasRepository.save(ferias);

        return true;
    }

    @Transactional
    public void excluirFuncionario(Long funcionarioId) {
        try {
            funcionarioRepository.deleteById(funcionarioId);
        } catch (DataIntegrityViolationException e) {
            // Assume que a exceção foi lançada devido à tentativa de excluir um funcionário que está de férias.
            // Esse diagnóstico depende do mapeamento específico do banco de dados e da aplicação.
            throw new IllegalStateException("Não é possível excluir o funcionário, pois o mesmo encontra-se de férias.");
        }
    }

    public List<Ferias> buscarTodos() {
        return feriasRepository.findAll();
    }
}
