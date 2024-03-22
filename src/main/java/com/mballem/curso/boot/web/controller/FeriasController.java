package com.mballem.curso.boot.web.controller;
import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.model.Ferias;
import com.mballem.curso.boot.service.FeriasService;
import com.mballem.curso.boot.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ferias")
public class FeriasController {

    @Autowired
    private FeriasService feriasService;
    @Autowired
    private FuncionarioService funcionarioService;

    // Método para abrir a página de registro de férias
    @GetMapping("/registro")
    public String abrirPaginaDeRegistro(Model model) {
        List<Funcionario> funcionarios = funcionarioService.buscarTodos(); // Método hipotético para buscar todos os funcionários
        model.addAttribute("funcionarios", funcionarios);
        return "ferias/registroFerias";
    }

    // Exemplo de método para calcular férias e exibir o resultado na página
    @GetMapping("/{funcionarioId}")
    public String calcularFerias(@PathVariable Long funcionarioId, Model model) {
        Ferias ferias = feriasService.calcularFerias(funcionarioId);
        model.addAttribute("ferias", ferias); // Adiciona o objeto ferias ao modelo para uso na página
        return "ferias/registroFerias"; // Substitua pelo nome do template que mostra o resultado
    }
}
