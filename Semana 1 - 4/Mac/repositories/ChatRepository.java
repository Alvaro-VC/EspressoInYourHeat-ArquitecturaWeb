package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c WHERE c.consumidor1.id = :consumidorId OR c.consumidor2.id = :consumidorId")
    List<Chat> findAllByConsumidor(Long consumidorId);
}
