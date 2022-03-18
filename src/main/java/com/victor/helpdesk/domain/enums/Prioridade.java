package com.victor.helpdesk.domain.enums;

public enum Prioridade {

    ALTA(0, "ALTA"), MEDIA(1, "MAEDIA"), BAIXA(2, "BAIXA");

    private Integer codigo;
    private String descriccao;

    Prioridade(Integer codigo, String descriccao) {
        this.codigo = codigo;
        this.descriccao = descriccao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescriccao() {
        return descriccao;
    }

    public static Prioridade toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Prioridade x: Prioridade.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválido");
    }
}
