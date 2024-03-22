package com.projetouposunipe.dao;

import com.projetouposunipe.domain.Departamento;
import org.springframework.stereotype.Repository;

//Essa anotação serve para que essa classe seja um bean gerenciado pelo spring
@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao{

}
