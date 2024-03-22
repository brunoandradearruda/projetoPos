package com.projetouposunipe.web.conversor;

import com.projetouposunipe.domain.Departamento;
import com.projetouposunipe.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


//recebemos um string, e o tipo da classe que deve ser convertida na interface generica
@Component
public class SpringToDepartamentoConverter implements Converter<String, Departamento> {
	
	//injeto 
	@Autowired
	DepartamentoService service;
	
	//verificamos se esta vazio, caso nao esteja, passamos para long o text que vem la da th:value 
	@Override
	public Departamento convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
