package com.example.pruebantt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pruebantt.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);
}
