package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
