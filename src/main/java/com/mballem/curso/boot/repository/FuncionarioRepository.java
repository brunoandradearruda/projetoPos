package com.mballem.curso.boot.repository;
import com.mballem.curso.boot.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
