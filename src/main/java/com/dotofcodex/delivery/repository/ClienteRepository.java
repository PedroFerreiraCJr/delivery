package com.dotofcodex.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotofcodex.delivery.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);
}
