package com.victor.helpdesk.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.helpdesk.domain.Chamado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChamadoDTO {

    private Integer id;

    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer prioridade;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;

    @NotNull(message = "O campo TECNICO é requerido")
    private Integer tecnico;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo OBSERVAÇÃO é requerido")
    private String observacao;

    private String nameTecnico;

    private String nameCliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dtAbertura = LocalDate.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dtFechamento;

    public ChamadoDTO(Chamado obj){
        this.id = obj.getId();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.cliente = obj.getCliente().getId();
        this.tecnico = obj.getTecnico().getId();
        this.titulo = obj.getTitulo();
        this.observacao = obj.getObservacoes();
        this.nameTecnico = obj.getTecnico().getName();
        this.nameCliente = obj.getCliente().getName();

        if (this.status.equals(2)){
            this.dtFechamento = LocalDate.now();
        }
    }


}
