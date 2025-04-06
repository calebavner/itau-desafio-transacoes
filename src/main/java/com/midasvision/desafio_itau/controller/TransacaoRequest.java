package com.midasvision.desafio_itau.controller;

import java.time.OffsetDateTime;

public class TransacaoRequest {

    private Double valor;
    private OffsetDateTime dataHora;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
