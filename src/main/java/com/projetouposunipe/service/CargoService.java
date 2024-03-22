package com.projetouposunipe.service;

import java.util.List;

import com.projetouposunipe.domain.Cargo;

public interface CargoService {
	void salvar(Cargo cargo);
	void editar(Cargo cargo);
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	List<Cargo> buscarTodos();
	boolean cargoTemFuncionario(Long id);
}
