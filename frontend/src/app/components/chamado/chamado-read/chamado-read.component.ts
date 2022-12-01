import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ChamadoService } from 'src/app/services/chamado.service';
import { Chamados } from '../../entities/Chamados';

@Component({
  selector: 'app-chamado-read',
  templateUrl: './chamado-read.component.html',
  styleUrls: ['./chamado-read.component.css']
})
export class ChamadoReadComponent implements OnInit {

  chamado: Chamados = {
    prioridade:   '',
    status:       '',
    titulo:       '',
    observacao:   '',
    tecnico:      '',
    cliente:      '',
    nameCliente:  '',
    nameTecnico:  '',
    dtAbertura:   '',
    dtFechamento: '',
  }

  constructor(
    private chamadoService: ChamadoService,
    private toastrService: ToastrService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }

   findById(): void {
    this.chamadoService.findById(this.chamado.id).subscribe(resposta => {
      this.toastrService.success('Chamado encontrado', 'Detalhes do chamado');
      this.chamado = resposta;
    }, ex => {
      console.log(ex);
    })
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

}