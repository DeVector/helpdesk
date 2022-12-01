import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { Credenciais } from '../entities/Credenciais';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  creds: Credenciais = {
    email: '',
    password: '',
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(
    private toastr: ToastrService,
    private service: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  logar(){
    this.service.authenticate(this.creds).subscribe(resposta => {
      this.service.successFullLoing(resposta.headers.get('Authorization').substring(7));
      this.toastr.success('Você logou!!', 'Logado')
      this.router.navigate(['']);
    }, () => {
      this.toastr.error('Usuário e/ou senha inválidos');
    })
  }

  validaCampos(): boolean {
    return this.email.valid && this.senha.valid;
  }

}
