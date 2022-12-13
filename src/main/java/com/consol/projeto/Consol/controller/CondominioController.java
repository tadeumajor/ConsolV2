package com.consol.projeto.Consol.controller;

import com.consol.projeto.Consol.model.Condominio;
import com.consol.projeto.Consol.model.Usuario;
import com.consol.projeto.Consol.repository.CondominioRepository;
import com.consol.projeto.Consol.repository.UsuarioRepository;
import com.consol.projeto.Consol.service.CondominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class CondominioController {

    @Autowired
    private CondominioRepository  condominioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CondominioService condominioService;

    @RequestMapping(value = "/cadastrarCondominio", method = RequestMethod.GET)
    public  String form(){
        return "condominio/formCondominio";
    }

    @RequestMapping(value = "/cadastrarCondominio", method = RequestMethod.POST)
    public  String form(@Valid Condominio condominio, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarCondominio";
        }
        condominioRepository.save(condominio);
        condominioService.calculavalorInvestimento(condominio.getCodigo());

        attributes.addFlashAttribute("mensagem", "Condominio cadastrado com sucesso!");
        return "redirect:/cadastrarCondominio";
    }

    //novo a ajustar
    @RequestMapping(value = "/condominios/editarCondominios/{codigo}")
    public ModelAndView updateForm(@Valid @PathVariable("codigo") Long codigo){
        ModelAndView mv = new ModelAndView("condominio/atualizaCondominio");

        Condominio condominio = condominioService.getCondominio(codigo);
        mv.addObject("condominio", condominio);
        return  mv;
    }

    @RequestMapping(value = "/condominios/editarCondominio/{codigo}", method = RequestMethod.POST)
    public String atualizarCondominio(@PathVariable("codigo") Long codigo, @Valid @ModelAttribute("condominio") Condominio condominio, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarCondominio";
        }

        condominioService.putCondominio(codigo, condominio, result, attributes);
        attributes.addFlashAttribute("mensagem", "Atualizado com sucesso!");
        return "redirect:/cadastrarCondominio";
    }


    @RequestMapping("/condominios")
    public ModelAndView listaCondominios(){

        ModelAndView mv = new ModelAndView("index");
        Iterable<Condominio> condominios = condominioRepository.findAll();
        mv.addObject("condominios", condominios);
        return  mv;
    }

    @RequestMapping(value = "/{codigo}", method= RequestMethod.GET)
    public ModelAndView detalhesCondominio(@PathVariable("codigo") long codigo){
        Condominio condominio = condominioRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("condominio/detalhesCondominio");
        mv.addObject("condominio", condominio);

        //retornar a lista de moradores do predio
        Iterable<Usuario> usuarios = usuarioRepository.findByCondominio(condominio);
        mv.addObject("usuarios", usuarios);
        return  mv;
    }

    @RequestMapping("/deletarCondominio")
    public String deleteCondominio(Long codigo){
        Condominio condominio = condominioRepository.findByCodigo(codigo);
        condominioRepository.delete(condominio);
        return "redirect:/condominios";
    }

    @RequestMapping("/deletarUsuario")
    public String deleteUsuario(String cpf){
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        usuarioRepository.delete(usuario);

        Condominio condominio = usuario.getCondominio();
        long codigoLong = condominio.getCodigo();
        String codigo = ""+codigoLong;
        return "redirect:/"+ codigo;
    }



    @RequestMapping(value = "/{codigo}", method= RequestMethod.POST)
    public String detalhesCondominioPost(@PathVariable("codigo") long codigo, @Valid  Usuario usuario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verfifique os campos abaixo!");
            return  "redirect:/{codigo}";
        }

        Condominio condominio = condominioRepository.findByCodigo(codigo);
        usuario.setCondominio(condominio);
        usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensagem", "Morador adicionado com sucesso!");
        return "redirect:/{codigo}";
    }
}
