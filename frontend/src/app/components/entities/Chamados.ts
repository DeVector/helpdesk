export interface Chamados {
    id?:          any;
    prioridade:   string;
    status:       string;
    cliente:      any;
    tecnico:      any;
    titulo:       string;
    observacao:   string;
    nameCliente:  string;
    nameTecnico:  string;
    dtAbertura?:   string;
    dtFechamento?: string;
}