package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(autorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Autor> criarAutor(@Valid @RequestBody Autor autor) {
        return ResponseEntity.ok(autorRepository.save(autor));
    }
}
