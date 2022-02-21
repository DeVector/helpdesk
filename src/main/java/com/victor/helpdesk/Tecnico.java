package com.victor.helpdesk;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa{

    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(int id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
