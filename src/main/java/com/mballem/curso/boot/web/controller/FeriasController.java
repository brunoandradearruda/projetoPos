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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
        List<Funcionario> funcionarios = funcionarioService.buscarTodos(); // Método para buscar todos os funcionários
        model.addAttribute("funcionarios", funcionarios);
        return "ferias/registroFerias";
    }

    // Método para lidar com o registro de férias
    @PostMapping("/registrar")
    public String registrarFerias(@RequestParam("funcionarioId") Long funcionarioId,
                                  @RequestParam("dataInicio") String dataInicio,
                                  Model model) {
        LocalDate inicioFerias = LocalDate.parse(dataInicio);
        feriasService.registrarFerias(funcionarioId, inicioFerias, 30); // Considerando 30 dias de férias por padrão

        return "redirect:/ferias/listagem"; // Redireciona para a listagem de funcionários em férias
    }

    // Método para listar funcionários em férias
    @GetMapping("/listar")
    public String listarFuncionariosEmFerias(Model model) {
        List<Ferias> listaDeFerias = feriasService.buscarTodos(); // Este método deve retornar a lista de férias
        model.addAttribute("funcionariosEmFerias", listaDeFerias);
        return "ferias/funcionarioEmFerias";
    }

}
