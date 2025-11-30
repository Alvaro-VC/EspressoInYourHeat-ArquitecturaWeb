import {Component, inject, ViewEncapsulation} from '@angular/core';
import {MatCard, MatCardContent} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ConsumidorService} from '../../services/consumidor-service';
import {Consumidor} from '../../model/consumidor';
import {NgClass, NgIf} from '@angular/common';
import {MatTab, MatTabContent, MatTabGroup, MatTabsModule} from '@angular/material/tabs';
import {PropietarioCafeteriaService} from '../../services/propietario-cafeteria-service';
import {PropietarioCafeteria} from '../../model/propietario-cafeteria';
import {User} from '../../model/user';
import {UserService} from '../../services/user-service';
@Component({
  selector: 'app-crear-cuenta-component',
  imports: [
    MatCard,
    MatCardContent,
    MatFormFieldModule,
    MatInput,
    MatButton,
    MatRadioGroup,
    MatRadioButton,
    FormsModule,
    ReactiveFormsModule,
    NgClass,
    NgIf,
    MatTabGroup,
    MatTab,
    MatTabContent,
    MatTabsModule
  ],
  templateUrl: './crear-cuenta-component.html',
  styleUrl: './crear-cuenta-component.css',
  encapsulation: ViewEncapsulation.None
})
export class CrearCuentaComponent {

  crearCuentaConsumidorForm: FormGroup;
  fb1: FormBuilder = inject(FormBuilder);
  private userService = inject(UserService);

  private consumidorService = inject(ConsumidorService);
  router = inject(Router);
  route: ActivatedRoute = inject(ActivatedRoute);

  crearCuentaPropietarioForm: FormGroup;
  fb2: FormBuilder = inject(FormBuilder);
  private propietarioService = inject(PropietarioCafeteriaService);

  errorMessageUserConst : string = "";
  errorMessageUserProp : string = "";
  tipoUsuario: string = 'consumidor';
  submittedConsumidor = false;
  submittedPropietario = false;

  constructor() {
    this.crearCuentaConsumidorForm = this.fb1.group({
      username: ["", Validators.required],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/)]],
      name: ["", Validators.required],
      apellido: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      sexo: ["", Validators.required],
      edad: ["", [Validators.required, Validators.min(16), Validators.max(120)]],
    });
    this.crearCuentaPropietarioForm = this.fb2.group({
      username: ["", Validators.required],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/)]],
      name: ["", Validators.required],
      apellido: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
    });
  }

  cambiarTipoUsuario(index: number) {
    this.tipoUsuario = index === 0 ? 'consumidor' : 'propietario';
  }

  onSubmitConsumidor() {
    this.submittedConsumidor = true;
    console.log('Formulario de consumidor enviado');
    if (this.crearCuentaConsumidorForm.valid) {
      console.log('Formulario validado');

      const form = this.crearCuentaConsumidorForm.value;

      let user: User = new User();
      user.username = form.username;
      user.password = form.password;

      console.log("Leyendo datos del usuario");
      this.userService.createUser(user).subscribe({
        next: (data) => {
          const userID = data.id;
          console.log("Dato a enviar para consmer: ", userID);
          let consumidor: Consumidor = new Consumidor();
          consumidor.name = form.name;
          consumidor.apellido = form.apellido;
          consumidor.email = form.email;
          consumidor.sexo = form.sexo;
          consumidor.edad = form.edad;
          consumidor.user = {id: userID} as User;

          console.log("Datos leídos del usuario");
          this.userService.saveUseRole(userID, 2).subscribe(() => {
            console.log("Rol de consumidor asignado");
            this.consumidorService.insert(consumidor).subscribe((data) => {
              console.log(data);
              console.log("Consumidor registrado");
              this.router.navigate(['/acceder']);
            })
          });
        },
        error: (err) => {
          console.error(err);
          if (err.status === 400 && err.error.includes('ya existe')) {
            this.errorMessageUserConst = 'El nombre de usuario ya está en uso. Elige otro.';
          } else {
            this.errorMessageUserConst = 'Ocurrió un error al crear el usuario.';
          }
        }
      });
      return;
    }
    if (this.crearCuentaConsumidorForm.invalid) {
      this.crearCuentaConsumidorForm.markAllAsTouched();
      console.log('Formulario inválido');
      this.crearCuentaConsumidorForm.markAsTouched();
      Object.keys(this.crearCuentaConsumidorForm.controls).forEach((campo) => {
        const control = this.crearCuentaConsumidorForm.get(campo);
        if (control && control.invalid) {
          console.warn(`Error en el campo: ${campo}`, control.errors);
        }
      });
      return;
    }
  }

  onSubmitPropietario() {
    this.submittedPropietario = true;
    console.log('Formulario de propietario enviado');

    if (this.crearCuentaPropietarioForm.valid) {
      console.log('Formulario validado');

      const form = this.crearCuentaPropietarioForm.value;

      let user: User = new User();
      user.username = form.username;
      user.password = form.password;

      console.log("Leyendo datos del usuario");
      this.userService.createUser(user).subscribe({
          next : (data) => {
            const userID = data.id;
            console.log("Dato a enviar para proper: ", userID);
            let propietario: PropietarioCafeteria = new PropietarioCafeteria();
            propietario.name = form.name;
            propietario.apellido = form.apellido;
            propietario.email = form.email;

            propietario.user = {id: userID} as User;


            console.log("Datos leídos del usuario");
            this.userService.saveUseRole(userID, 2).subscribe(() => {
              console.log("Rol de propietario asignado");
              this.propietarioService.insert(propietario).subscribe((data) => {
                console.log(data);
                console.log("Propietario registrado");
                this.router.navigate(['/acceder']);
              })
            })
          },
        error: (err) => {
          console.error(err);
          if (err.status === 400 && err.error.includes('ya existe')) {
            this.errorMessageUserProp = 'El nombre de usuario ya está en uso. Elige otro.';
          } else {
            this.errorMessageUserProp = 'Ocurrió un error al crear el usuario.';
          }
        }
      });
      return;
    }
    if (this.crearCuentaPropietarioForm.invalid) {
      this.crearCuentaPropietarioForm.markAllAsTouched();
      console.log('Formulario inválido');
      this.crearCuentaPropietarioForm.markAsTouched();
      Object.keys(this.crearCuentaPropietarioForm.controls).forEach((campo) => {
        const control = this.crearCuentaPropietarioForm.get(campo);
        if (control && control.invalid) {
          console.warn(`Error en el campo: ${campo}`, control.errors);
        }
      });
      return;
    }
  }

}
