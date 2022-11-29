import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TecnicoService } from 'src/app/services/tecnico.service';
import { Tecnico } from '../../entities/Tecnico';

@Component({
  selector: 'app-tecnico-create',
  templateUrl: './tecnico-create.component.html',
  styleUrls: ['./tecnico-create.component.css']
})
export class TecnicoCreateComponent implements OnInit {

  tecnico: Tecnico ={
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
    private service: TecnicoService,
    private toastr:  ToastrService,
    private router:  Router
    ) { }

  ngOnInit(): void { }

  
  create(): void {
    this.service.create(this.tecnico).subscribe(() => {
      this.toastr.success('Técnico cadastrado com sucesso', 'Cadastrado');
      this.router.navigate(['tecnicos'])
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

    if(this.tecnico.perfis.includes(perfil)){
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil), 1);
    } else {
      this.tecnico.perfis.push(perfil);
    }

  }
  
  validarCampos(): boolean {
    return this.name.valid && this.email.valid && this.password.valid && this.cpf.valid;
  }

}
