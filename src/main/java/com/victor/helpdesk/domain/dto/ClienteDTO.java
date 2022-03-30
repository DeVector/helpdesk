package com.victor.helpdesk.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.helpdesk.domain.Cliente;
import com.victor.helpdesk.domain.enums.Perfil;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data

public class ClienteDTO {

    protected Integer id;
    @NotNull(message = "O campo NOME é requerido")
    protected String name;
    @NotNull(message = "O campo CPF é requerido")
    @CPF
    protected String cpf;
    @NotNull(message = "O campo EMAIL é requerido")
    protected String email;
    @NotNull(message = "O campo PASSWORD é requerido")
    protected String password;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate localDate = LocalDate.now();

    public ClienteDTO() {
        super();
        addPerfis(Perfil.TECNICO);
    }

    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.password = obj.getPassword();
        this.localDate = obj.getLocalDate();
    }


    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfis) {
        this.perfis.add(perfis.getCodigo());
    }

}
