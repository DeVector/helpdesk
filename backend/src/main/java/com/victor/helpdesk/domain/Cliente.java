package com.victor.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victor.helpdesk.domain.dto.ClienteDTO;
import com.victor.helpdesk.domain.dto.TecnicoDTO;
import com.victor.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor

@Entity
public class Cliente extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(String name, String cpf, String email, String password) {
        super(name, cpf, email, password);
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.localDate = obj.getLocalDate();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
