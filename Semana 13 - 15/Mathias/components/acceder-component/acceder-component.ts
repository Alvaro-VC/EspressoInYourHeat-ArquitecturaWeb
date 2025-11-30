import {Component, inject} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {MatFormField} from '@angular/material/form-field';
import {MatInput, MatLabel} from '@angular/material/input';

import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LoginService} from '../../services/login-service';
import {RequestDto} from '../../model/request-dto';
import {ResponseDto} from '../../model/response-dto';
import {MatCard, MatCardContent} from '@angular/material/card';


@Component({
  selector: 'app-acceder-component',
  imports: [
    RouterLink,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
    MatCard,
    MatCardContent,

  ],
  templateUrl: './acceder-component.html',
  styleUrl: './acceder-component.css',
})
export class AccederComponent {
  username: string = '';
  password: string = '';
  router: Router = inject(Router);
  accederCuentaForm: FormGroup;
  fb = inject(FormBuilder);
  loginService: LoginService = inject(LoginService);
  rol: any;
  constructor() {
    this.accederCuentaForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  ngOnInit() {
    if(localStorage.getItem('token')!=null){
      localStorage.clear();//borra todos los items
      console.log("Token y items eliminados");
    }
    this.loadForm()
  }

  loadForm(): void {
    console.log("Form");
  }

  onSubmit() {
    if (this.accederCuentaForm.valid) {
      const requestDto: RequestDto = new RequestDto()
      requestDto.username = this.accederCuentaForm.value.username;
      requestDto.password = this.accederCuentaForm.value.password;
      let responseDTO: ResponseDto = new ResponseDto();
      this.loginService.login(requestDto).subscribe({
        next: (data: ResponseDto): void => {
          console.log("Login response ROLs:", data.roles);
          console.log("Login response ROL:", data.roles[0]);
          localStorage.setItem('rol', data.roles[0]);
          localStorage.setItem('usuarioId', data.usuarioId?.toString() ?? '')
          this.rol = localStorage.getItem('rol');
          alert("El logeo ha sido un exito");
          if (this.rol === 'ROLE_CONSUMIDOR'){
            this.router.navigate(['/cafeterias']);
          }
          if (this.rol === 'ROLE_PROPIETARIOCAFETERIA'){
            this.router.navigate(['/propietariointerfaz']);
          }

        },
        error: (error: any) => {
          console.error("Hay un error");
          console.error(error);
          alert("Las credenciasles no existen o son incorrectas");
          this.router.navigate(['/acceder']);
        }
      })
    } else {
      alert("Formulario no válido!")
      console.log("Formulario no válido");
    }
  }
}
