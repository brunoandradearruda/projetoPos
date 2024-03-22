package com.mballem.curso.boot.model;

import java.time.LocalDate;

public class Ferias {
    private LocalDate inicioFerias;
    private LocalDate fimFerias;
    private int totalDias;

    // Construtor, getters e setters
    public Ferias(LocalDate inicioFerias, LocalDate fimFerias, int totalDias) {
        this.inicioFerias = inicioFerias;
        this.fimFerias = fimFerias;
        this.totalDias = totalDias;
    }

    // Getters e setters aqui
}
