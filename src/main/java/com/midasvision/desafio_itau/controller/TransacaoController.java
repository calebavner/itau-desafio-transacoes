package com.midasvision.desafio_itau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
public class TransacaoController {

    private final RepoTransacao repoTransacao;

    public TransacaoController(RepoTransacao repoTransacao) {
        this.repoTransacao = repoTransacao;
    }

    @PostMapping(value = "/transacao")
    public ResponseEntity cadastrar(@RequestBody TransacaoRequest request) {

        if(validarTransacao(request)) {
            repoTransacao.salvar(request);
            return ResponseEntity
                    .status(HttpStatus.valueOf(201))
                    .build();
        } else if(!validarTransacao(request)) {
            return ResponseEntity
                    .status(HttpStatus.valueOf(422))
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.valueOf(400))
                .build();
    }

    @DeleteMapping(value = "/transacao")
    public ResponseEntity limpar() {
        repoTransacao.limpar();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/transacao")
    public List<TransacaoRequest> listar() {
        return repoTransacao.listar();
    }

    @GetMapping(value = "/estatisticas")
    public DoubleSummaryStatistics estatisticas() {

        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);
        
        List<TransacaoRequest> response = listar().stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .toList();

        DoubleSummaryStatistics estatisticas = response.stream()
                .mapToDouble(x -> Double.valueOf(x.getValor()))
                .summaryStatistics();

        return estatisticas;
    }



    private boolean validarTransacao(TransacaoRequest request) {
        return request.getDataHora().isBefore(OffsetDateTime.now())
                && request.getValor() >= 0D;
    }
}
