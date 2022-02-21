package com.victor.helpdesk.domain.enums;

public enum Status {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descrig;

    Status(Integer codigo, String descrig) {
        this.codigo = codigo;
        this.descrig = descrig;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescrig() {
        return descrig;
    }

    private static Status toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (Status x : Status.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
