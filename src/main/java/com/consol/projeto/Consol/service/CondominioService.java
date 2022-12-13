package com.consol.projeto.Consol.service;

import com.consol.projeto.Consol.model.Condominio;
import com.consol.projeto.Consol.repository.CondominioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CondominioService {

    @Autowired
    CondominioRepository condominioRepository;

    public Condominio getCondominio(Long id) {
        Optional<Condominio> opt = Optional.ofNullable(condominioRepository.findByCodigo(id));

        if (opt.isPresent()) {
            Condominio condominio = opt.get();
            return condominio;
        } else {
            return null;
        }
    }


    public Condominio putCondominio(Long id, @Valid Condominio condominio, BindingResult result, RedirectAttributes attributes) {
        Optional<Condominio> opt = condominioRepository.findById(id);

        if (opt.isPresent()) {
            Condominio cond = opt.get();
            cond.setNome(condominio.getNome());
            cond.setRua(condominio.getRua());
            cond.setBairro(condominio.getBairro());
            cond.setNumero(condominio.getNumero());
            cond.setCep(condominio.getCep());
            cond.setConsumoMedioMesKwh(condominio.getConsumoMedioMesKwh());
            cond.setQtdPaineis(condominio.getQtdPaineis());
            cond.setQtdPaineis(condominio.getQtdPaineis());
            cond.setValorPaineis(condominio.getValorPaineis());
            cond.setValorAproximado(condominio.getValorAproximado());

            calculavalorInvestimento(cond.getCodigo());
            condominioRepository.save(cond);

            return cond;
        } else {
            return null;
        }
    }

        public Condominio validaCondominio(long id) {
            Optional<Condominio> condominioBusca = condominioRepository.findById(id);
            if (condominioBusca.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            }
            return condominioBusca.get();
        }

        public void calculavalorInvestimento(long id){

            // considerando media anual da cidade de BH
            double radiacaoSolar = 5.13;

            Condominio condominioBusca = validaCondominio(id);

            double consumoDia = condominioBusca.getConsumoMedioMesKwh()/30;
            double kwPico = consumoDia/radiacaoSolar;

            //conversao de kw para wat
            kwPico = kwPico*1000;
            int placaSolar330 = 330;
            int qtdPaineis = (int) (kwPico/placaSolar330);

            condominioBusca.setQtdPaineis(qtdPaineis);

            //base sendo 1200 painel + 300 por instalacao
            double vlrPainel330 = 1500;
            condominioBusca.setValorPaineis(1200);

            double precoInvesor=0;
            if(kwPico<=2000){
                precoInvesor = 2000;
            } else if(kwPico>2000 && kwPico<=3000){
                precoInvesor=5000;
            } else if(kwPico>3000 && kwPico<=5000){
                precoInvesor=6000;
            } else if(kwPico>5000 && kwPico<=12500){
                precoInvesor=15000;
            } else if(kwPico>12500 && kwPico<=25000){
                precoInvesor=18000;
            } else if(kwPico>25000) {
                precoInvesor = 40000;
            }

            double valorTotal = (qtdPaineis*vlrPainel330) + precoInvesor;
            condominioBusca.setValorAproximado(valorTotal);
            condominioRepository.save(condominioBusca);

        }

}
