import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import { Router } from '@angular/router';
interface ChatContact {
  id: number;
  name: string;
  avatarUrl: string;
  subtitle?: string;
  lastMessage: string;
  time: string;
}

interface ChatMessage {
  id: number;
  from: 'me' | 'them';
  text: string;
  time: string;
}

interface NewConnection {
  id: number;
  avatarUrl: string;
}
@Component({
  selector: 'app-chat-component',
  imports: [
    FormsModule,
    NgClass,
    NgForOf,
    NgIf
  ],
  templateUrl: './chat-component.html',
  styleUrl: './chat-component.css',
})
export class ChatComponent {
  constructor(private router: Router) {}

  // Usuario actual (Paulo)
  currentUser = {
    name: 'Paulo Alvarez Gutierrez',
    avatarUrl: 'assets/img/paulo-avatar.png'
  };

  // Contacto activo (null al entrar a la pantalla)
  activeContact: ChatContact | null = null;

  // Nuevas conexiones (solo visual)
  newConnections: NewConnection[] = [
    { id: 1, avatarUrl: 'assets/img/conexion-1.png' },
    { id: 2, avatarUrl: 'assets/img/conexion-2.png' },
    { id: 3, avatarUrl: 'assets/img/conexion-3.png' },
    { id: 4, avatarUrl: 'assets/img/conexion-4.png' }
  ];

  // Chats recientes (lista izquierda)
  recentChats: ChatContact[] = [
    {
      id: 1,
      name: 'Lucero',
      avatarUrl: 'assets/img/luceromatch.jpg',
      lastMessage: 'Hola',
      time: '12:00'
    },
    {
      id: 2,
      name: 'Marcelo',
      avatarUrl: 'assets/img/marcelo.png',
      lastMessage: 'Oh, en serio',
      time: '13:00'
    },
    {
      id: 3,
      name: 'Fabiana',
      avatarUrl: 'assets/img/fabiana.png',
      lastMessage: 'Hay una cafetería...',
      time: '9:00'
    }
  ];

  /**
   * Conversaciones separadas por contacto.
   * Empiezan vacías: se van llenando solo con lo que el usuario escriba.
   */
  private conversations: { [contactId: number]: ChatMessage[] } = {};

  messages: ChatMessage[] = [];


  draftMessage = '';


  openChat(chat: ChatContact): void {
    this.activeContact = chat;
    this.messages = [...(this.conversations[chat.id] ?? [])];
  }


  sendMessage(): void {
    const trimmed = this.draftMessage.trim();
    if (!trimmed || !this.activeContact) {
      return;
    }

    const contactId = this.activeContact.id;

    const currentConv = this.conversations[contactId] ?? [];
    const newId = currentConv.length
      ? Math.max(...currentConv.map(m => m.id)) + 1
      : 1;

    const newMessage: ChatMessage = {
      id: newId,
      from: 'me',
      time: 'Ahora',
      text: trimmed
    };

    const updatedConv = [...currentConv, newMessage];
    this.conversations[contactId] = updatedConv;
    this.messages = updatedConv;

    // actualizar resumen en la lista izquierda
    this.recentChats = this.recentChats.map(c =>
      c.id === contactId
        ? { ...c, lastMessage: trimmed, time: 'Ahora' }
        : c
    );

    this.draftMessage = '';
  }



  onEditarPerfil(): void {
    this.router.navigateByUrl('/editar-perfil');
  }

  onRegresar(): void {
    this.router.navigateByUrl('/match');
  }

  onHome(): void {
    this.router.navigateByUrl('/');
  }

  onDescubrirMas(): void {
    this.router.navigateByUrl('/match');
  }

  onVerCafeterias(): void {
    this.router.navigateByUrl('/cafeterias');
  }
}
