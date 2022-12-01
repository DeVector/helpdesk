import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ClienteService } from 'src/app/services/cliente.service';
import { TecnicoService } from 'src/app/services/tecnico.service';
import { Chamados } from '../../entities/Chamados';
import { Cliente } from '../../entities/Cliente';
import { Tecnico } from '../../entities/Tecnico';

@Component({
  selector: 'app-chamado-update',
  templateUrl: './chamado-update.component.html',
  styleUrls: ['./chamado-update.component.css']
})
export class ChamadoUpdateComponent implements OnInit {

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
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get('id');
    this.findById();
    this.findAllClinetes();
    this.findAllTecnicos();
   }

   findById(): void {
    this.chamadoService.findById(this.chamado.id).subscribe(resposta => {
      this.chamado = resposta;
    }, ex => {
      console.log(ex);
    })
   }

   update(): void {
    this.chamadoService.update(this.chamado).subscribe(resposta => {
      this.toastrService.success('Chamado atualizado com sucesso', 'Atualizar Chamado');
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

  returnStatus(status: any){
    if (status == '0'){
      return 'ABERTO';
    } else if (status == '1') {
      return 'ANDAMENTO';
    } else {
      return 'ENCERRADO';
    }
  }

  returnPrioridade(prioridade: any) {
    if( prioridade == '0'){
      return 'ALTA';
    } else if (prioridade == '1'){
      return 'MEDIA';
    } else {
      return 'BAIXA';
    }
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