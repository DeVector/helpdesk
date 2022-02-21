package com.victor.helpdesk;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(int id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }
}
