package edu.ifmt.colecaodejogos;

import edu.ifmt.colecaodejogos.model.Jogo;
import edu.ifmt.colecaodejogos.model.Status;
import edu.ifmt.colecaodejogos.model.Plataforma;
import edu.ifmt.colecaodejogos.repository.Jogos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private Jogos jogos;

    @GetMapping("/novo")
    public ModelAndView novoJogo() {
        ModelAndView mv = new ModelAndView("CadastroJogo");
        mv.addObject("jogo", new Jogo());
        return mv;
    }

    @PostMapping
    public ModelAndView salvarJogo(@Validated Jogo jogo, Errors erros, RedirectAttributes attributes) {
        if (erros.hasErrors()) {
            ModelAndView mv = new ModelAndView("CadastroJogo");
            mv.addObject("jogo", jogo);
            return mv;
        }

        jogos.save(jogo);
        attributes.addFlashAttribute("message", "Jogo salvo com sucesso!");
        return new ModelAndView("redirect:/jogos/novo");
    }

    @GetMapping({"/", ""})
    public ModelAndView pesquisaJogo() {
        List<Jogo> todosJogos = jogos.findAll();
        ModelAndView mv = new ModelAndView("PesquisaJogo");
        mv.addObject("jogos", todosJogos);
        return mv;
    }


    @GetMapping("{id}")
    public ModelAndView editarJogo(@PathVariable("id") Long id, RedirectAttributes attributes) {
        Optional<Jogo> jogoOptional = jogos.findById(id);
        if (jogoOptional.isEmpty()) {
            attributes.addFlashAttribute("error", "Jogo não encontrado.");
            return new ModelAndView("redirect:/jogos"); // Redirecionamento caso não encontre jogos
        }
        ModelAndView mv = new ModelAndView("CadastroJogo");
        mv.addObject("jogo", jogoOptional.get());
        return mv;
    }

    @DeleteMapping("{id}")
    public ModelAndView apagarJogo(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            jogos.deleteById(id);
            attributes.addFlashAttribute("message", "Jogo excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao excluir o jogo. Por favor, tente novamente.");
            }
        return new ModelAndView("redirect:/jogos");
    }

    @ModelAttribute("todasPlataforma")
    public List<Plataforma> todasPlataforma() {
        return Arrays.asList(Plataforma.values());
    }

    @ModelAttribute("todosStatus")
    public List<Status> todosStatus() {
        return Arrays.asList(Status.values());
    }
}