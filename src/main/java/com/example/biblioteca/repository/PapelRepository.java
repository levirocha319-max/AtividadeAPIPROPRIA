package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    Optional<Papel> findByNome(String nome);
}
