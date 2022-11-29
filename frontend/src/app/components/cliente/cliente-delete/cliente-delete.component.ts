import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from 'src/app/services/cliente.service';
import { Cliente } from '../../entities/Cliente';

@Component({
  selector: 'app-cliente-delete',
  templateUrl: './cliente-delete.component.html',
  styleUrls: ['./cliente-delete.component.css']
})

export class ClienteDeleteComponent implements OnInit {

  cliente: Cliente ={
    id:          '',
    name:        '',
    cpf:         '',
    email:       '',
    password:    '',
    perfis:      [],
    localDate:   ''
  }

  constructor(
    private service: ClienteService,
    private toastr:  ToastrService,
    private router:  Router,
    private route: ActivatedRoute,
    ) { }

  ngOnInit(): void { 
    this.cliente.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }

  findById(): void {
    this.service.findById(this.cliente.id).subscribe(resposta => {
      resposta.perfis = [];
      this.cliente = resposta;
    })
  }
  
  delete(): void {
    this.service.delete(this.cliente.id).subscribe(() => {
      this.toastr.success('cliente deletado com sucesso', 'Delete');
      this.router.navigate(['clientes'])
    }, ex => {
      if(ex.error.list) {
        ex.error.list.forEach( element => {
          this.toastr.error(element.fildName);
        });
      } else {
        this.toastr.error(ex.error.message);
      }
    })
  }

}

