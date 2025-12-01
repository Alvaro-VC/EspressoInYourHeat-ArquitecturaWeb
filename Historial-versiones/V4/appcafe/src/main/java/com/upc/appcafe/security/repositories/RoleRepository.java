package com.upc.appcafe.security.repositories;

import com.upc.appcafe.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
