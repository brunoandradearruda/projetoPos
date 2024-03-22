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
                                  RedirectAttributes redirectAttributes) {
        LocalDate inicioFerias = LocalDate.parse(dataInicio);
        boolean sucesso = feriasService.registrarFerias(funcionarioId, inicioFerias, 30); // Considerando 30 dias de férias por padrão
            if (!sucesso) {
            redirectAttributes.addFlashAttribute("limiteExcedido", true);
            redirectAttributes.addFlashAttribute("mensagemErro", "Já existe férias em gozo no intervalo de 12 meses.");
            return "redirect:/ferias/registro";
        }

        // Caso o registro seja bem-sucedido, redirecionar para outra página conforme necessário
        return "redirect:/ferias/listar"; // Exemplo de redirecionamento após sucesso
    }

    // Método para listar funcionários em férias
    @GetMapping("/listar")
    public String listarFuncionariosEmFerias(Model model) {
        List<Ferias> listaDeFerias = feriasService.buscarTodos(); // Este método deve retornar a lista de férias
        model.addAttribute("funcionariosEmFerias", listaDeFerias);
        return "ferias/funcionarioEmFerias";
    }

}
