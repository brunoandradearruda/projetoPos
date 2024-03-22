package com.projetouposunipe.model;

import com.projetouposunipe.domain.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ferias")
public class Ferias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate inicioFerias;
    private LocalDate fimFerias;
    private int totalDias;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario; // Supondo que Funcionario é uma entidade

    // Construtor padrão exigido pelo JPA
    public Ferias() {
    }

    // Construtor com parâmetros
    public Ferias(LocalDate inicioFerias, LocalDate fimFerias, int totalDias, Funcionario funcionario) {
        this.inicioFerias = inicioFerias;
        this.fimFerias = fimFerias;
        this.totalDias = totalDias;
        this.funcionario = funcionario;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInicioFerias() {
        return inicioFerias;
    }

    public void setInicioFerias(LocalDate inicioFerias) {
        this.inicioFerias = inicioFerias;
    }

    public LocalDate getFimFerias() {
        return fimFerias;
    }

    public void setFimFerias(LocalDate fimFerias) {
        this.fimFerias = fimFerias;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
