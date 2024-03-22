package com.projetouposunipe.service;

import java.time.LocalDate;
import java.util.List;

import com.projetouposunipe.domain.Funcionario;

public interface FuncionarioService {
	void salvar(Funcionario funcionario);
	void editar(Funcionario funcionario);
	void excluir(Long id);
	
	Funcionario buscarPorId(Long id);
	List <Funcionario> buscarTodos();
	List <Funcionario> buscarPorNome(String nome);
	List <Funcionario> buscarPorCargo(long id);
	List <Funcionario> buscarPorData(LocalDate entrada, LocalDate saida);
}
