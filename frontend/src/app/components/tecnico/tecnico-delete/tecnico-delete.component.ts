import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TecnicoService } from 'src/app/services/tecnico.service';
import { Tecnico } from '../../entities/Tecnico';

@Component({
  selector: 'app-tecnico-delete',
  templateUrl: './tecnico-delete.component.html',
  styleUrls: ['./tecnico-delete.component.css']
})

export class TecnicoDeleteComponent implements OnInit {

  tecnico: Tecnico ={
    id:          '',
    name:        '',
    cpf:         '',
    email:       '',
    password:    '',
    perfis:      [],
    localDate:   ''
  }

  constructor(
    private service: TecnicoService,
    private toastr:  ToastrService,
    private router:  Router,
    private route: ActivatedRoute,
    ) { }

  ngOnInit(): void { 
    this.tecnico.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }

  findById(): void {
    this.service.findById(this.tecnico.id).subscribe(resposta => {
      resposta.perfis = [];
      this.tecnico = resposta;
    })
  }
  
  delete(): void {
    this.service.delete(this.tecnico.id).subscribe(() => {
      this.toastr.success('Técnico deletado com sucesso', 'Delete');
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

}

