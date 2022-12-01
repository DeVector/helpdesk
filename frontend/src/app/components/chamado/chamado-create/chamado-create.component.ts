import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ClienteService } from 'src/app/services/cliente.service';
import { TecnicoService } from 'src/app/services/tecnico.service';
import { Chamados } from '../../entities/Chamados';
import { Cliente } from '../../entities/Cliente';
import { Tecnico } from '../../entities/Tecnico';

@Component({
  selector: 'app-chamado-create',
  templateUrl: './chamado-create.component.html',
  styleUrls: ['./chamado-create.component.css']
})
export class ChamadoCreateComponent implements OnInit {

  chamado: Chamados = {
    prioridade:  '',
    status:      '',
    titulo:      '',
    observacao:  '',
    tecnico:     '',
    cliente:     '',
    nameCliente: '',
    nameTecnico: '',
  }

  clientes: Cliente[] = [];
  tecnicos: Tecnico[] = [];

  prioridade:  FormControl = new FormControl(null, [Validators.required]);
  status:      FormControl = new FormControl(null, [Validators.required]);
  titulo:      FormControl = new FormControl(null, [Validators.required]);
  observacoes: FormControl = new FormControl(null, [Validators.required]);
  tecnico:     FormControl = new FormControl(null, [Validators.required]);
  cliente:     FormControl = new FormControl(null, [Validators.required]);

  constructor(
    private chamadoService: ChamadoService,
    private tecnicoService: TecnicoService,
    private clienteService: ClienteService,
    private toastrService: ToastrService,
    private router: Router,
  ) { }

  ngOnInit(): void { 
    this.findAllClinetes();
    this.findAllTecnicos();
   }

   create(): void {
    this.chamadoService.create(this.chamado).subscribe(resposta => {
      this.toastrService.success('Chamado criado com sucesso', 'Novo Chamado');
      this.router.navigate(['chamados']);
    }, ex => {
      this.toastrService.error(ex.error.error);
    })
   }

  findAllClinetes(): void {
    this.clienteService.findAll().subscribe(resposta => {
      this.clientes = resposta;
    });
  }

  findAllTecnicos(): void {
    this.tecnicoService.findAll().subscribe(resposta => {
      this.tecnicos = resposta;
    });
  }

  validarCampos(): boolean {
    return this.prioridade.valid &&
    this.status.valid &&
    this.titulo.valid &&
    this.observacoes.valid &&
    this.tecnico.valid &&
    this.cliente.valid;
  }

}
