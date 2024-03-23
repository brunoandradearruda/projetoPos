package com.projetouposunipe.web.controller;

import com.projetouposunipe.domain.Funcionario;
import com.projetouposunipe.model.Ferias;
import com.projetouposunipe.service.FeriasService;
import com.projetouposunipe.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        try {
            LocalDate inicioFerias = LocalDate.parse(dataInicio);
            feriasService.registrarFerias(funcionarioId, inicioFerias, 30); // Assumindo 30 dias de férias
            model.addAttribute("mensagemSucesso", "Férias registradas com sucesso.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", e.getMessage());
            // Também adicione os funcionários novamente ao modelo para repovoar o formulário
            List<Funcionario> funcionarios = funcionarioService.buscarTodos();
            model.addAttribute("funcionarios", funcionarios);
            return "ferias/registroFerias"; // Retorna ao formulário de registro com a mensagem de erro
        }
        // Se for necessário redirecionar para outra página e mostrar a mensagem lá, use RedirectAttributes
        // E redirecione para a página desejada. Exemplo:
        // redirectAttributes.addFlashAttribute("mensagemSucesso", "Férias registradas com sucesso.");
        // return "redirect:/ferias/listar";
        return "ferias/registroFerias"; // Mantenha o usuário na página de registro e mostre a mensagem de sucesso
    }



    // Método para listar funcionários em férias
    @GetMapping("/listar")
    public String listarFuncionariosEmFerias(Model model) {
        List<Ferias> listaDeFerias = feriasService.buscarTodos(); // Este método deve retornar a lista de férias
        model.addAttribute("funcionariosEmFerias", listaDeFerias);
        return "ferias/funcionarioEmFerias";
    }



}
