package com.midasvision.desafio_itau.controller;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepoTransacao {

    private List<TransacaoRequest> transacoes = new ArrayList<>();

    public void salvar(TransacaoRequest request) {
        transacoes.add(request);
    }

    public void limpar() {
        transacoes.clear();
    }

    public List<TransacaoRequest> listar() {
        return transacoes;
    }
}
