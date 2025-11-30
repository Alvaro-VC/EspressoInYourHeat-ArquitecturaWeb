import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule, NgClass} from '@angular/common';
import {NgModel} from '@angular/forms';
interface MatchProfile {
  id: number;
  name: string;
  age: number;
  description: string;
  imageUrl: string;
}

@Component({
  selector: 'app-match-component',
  imports: [
    NgClass,
    CommonModule
  ],
  templateUrl: './match-component.html',
  styleUrl: './match-component.css',
})
export class MatchComponent {
  constructor(private router: Router) {
  }
  // Usuario actual (para la foto + nombre de la izquierda)
  currentUser = {
    name: 'Paulo Alvarez Gutierrez',
    avatarUrl: 'assets/img/usuario.jpg'
  };

  // Perfiles que aparecerán en la tarjeta central
  profiles: MatchProfile[] = [
    {
      id: 1,
      name: 'Mariana',
      age: 29,
      imageUrl: 'assets/img/marianamatch.jpg',
      description:
        '¡Hola! Soy Mariana, tengo 29 años y el café es mi pequeño placer diario. Mis favoritos son el americano y en especial el mocha, porque no hay mejor combinación que café y chocolate. Me encanta pasar tiempo en cafeterías acogedoras, disfrutando de una buena charla. Estoy aquí para conocer a alguien con quien compartir esos momentos :)'
    },
    {
      id: 2,
      name: 'Andrea',
      age: 24,
      imageUrl: 'assets/img/andrea-match.jpg',
      description:
        'Soy Andrea, fan del latte con arte en la espuma. Me encanta visitar cafeterías nuevas con temáticas rockeras u ochenteras los fines de semana y tomar fotos para mi Instagram de café.'
    },
    {
      id: 3,
      name: 'Sofía',
      age: 27,
      imageUrl: 'assets/img/sofia-matche.jpg',
      description:
        'Amo el cold brew y los espacios tranquilos para leer o tener mi espacio co-working. Siempre estoy buscando la próxima cafetería escondida en la ciudad, si coincidimos compartimos mi café favorito en Lucio Caffe.'
    },
    {
      id: 4,
      name: 'Valeria',
      age: 26,
      imageUrl: 'assets/img/valeria-match.jpg',
      description:
        'Me gusta el cappuccino bien espumoso y las conversaciones largas. Si conoces un buen lugar con terraza, ya tenemos plan.'
    }
  ];

  // índice actual en el array de perfiles
  currentIndex = 0;

  // Estado de animación de la tarjeta
  animationState: 'idle' | 'like' | 'reject' = 'idle';

  // Historial (por si luego quieres usarlo)
  likedProfiles: MatchProfile[] = [];
  rejectedProfiles: MatchProfile[] = [];

  // Perfil que se está mostrando
  get currentProfile(): MatchProfile | null {
    return this.profiles[this.currentIndex] ?? null;
  }

  // ========== Acciones de match ==========

  onLikeClick(): void {
    if (!this.currentProfile || this.animationState !== 'idle') return;
    this.animationState = 'like';
  }

  onRejectClick(): void {
    if (!this.currentProfile || this.animationState !== 'idle') return;
    this.animationState = 'reject';
  }

  // Botón "DESCUBRIR" de arriba: lo tratamos como un "like"
  onDiscoverClick(): void {
    this.onLikeClick();
  }

  // Se dispara cuando termina la animación CSS
  onCardAnimationEnd(): void {
    const profile = this.currentProfile;
    if (!profile) {
      this.animationState = 'idle';
      return;
    }

    if (this.animationState === 'like') {
      this.likedProfiles.push(profile);
    } else if (this.animationState === 'reject') {
      this.rejectedProfiles.push(profile);
    }

    if (this.animationState !== 'idle') {
      // Avanzar al siguiente perfil
      this.currentIndex++;

      // Si no hay más perfiles, dejamos currentProfile en null
      if (this.currentIndex >= this.profiles.length) {
        this.currentIndex = this.profiles.length; // se queda sin perfil
      }
    }

    this.animationState = 'idle';
  }

  // ========== Navegación barra inferior ==========

  onEditarPerfil(): void {
    this.router.navigateByUrl('/editar-perfil');
  }

  onRegresar(): void {
    this.router.navigateByUrl('/chat'); // o /comunidad /home, como tú quieras
  }

  onHome(): void {
    this.router.navigateByUrl('/home');
  }

  onDescubrirMas(): void {
    this.router.navigateByUrl('/match'); // ya estás aquí, pero podría ir a otra sección
  }

  onVerCafeterias(): void {
    this.router.navigateByUrl('/cafeterias');
  }

}
