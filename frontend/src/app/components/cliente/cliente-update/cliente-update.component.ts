import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from 'src/app/services/cliente.service';
import { Cliente } from '../../entities/Cliente';

@Component({
  selector: 'app-cliente-update',
  templateUrl: './cliente-update.component.html',
  styleUrls: ['./cliente-update.component.css']
})

export class ClienteUpdateComponent implements OnInit {

  cliente: Cliente ={
    id:          '',
    name:        '',
    cpf:         '',
    email:       '',
    password:    '',
    perfis:      [],
    localDate:   ''
  }

  name: FormControl = new  FormControl(null, Validators.minLength(3));
  cpf: FormControl = new   FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  password: FormControl = new FormControl(null, Validators.minLength(3));

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
  
  update(): void {
    this.service.update(this.cliente).subscribe(() => {
      this.toastr.success('cliente atualizado com sucesso', 'Update');
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

  addPerfil(perfil: any): void {

    if(this.cliente.perfis.includes(perfil)){
      this.cliente.perfis.splice(this.cliente.perfis.indexOf(perfil), 1);
    } else {
      this.cliente.perfis.push(perfil);
    }

  }
  
  validarCampos(): boolean {
    return this.name.valid && this.email.valid && this.password.valid && this.cpf.valid;
  }

}
