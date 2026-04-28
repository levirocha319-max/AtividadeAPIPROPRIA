package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LivroRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroController(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody RequisicaoLivro requisicaoLivro) {
        Optional<Autor> autor = autorRepository.findById(requisicaoLivro.getIdAutor());
        if (autor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Livro livro = new Livro(requisicaoLivro.getTitulo(), autor.get());
        return ResponseEntity.ok(livroRepository.save(livro));
    }

    public static class RequisicaoLivro {

        @NotBlank
        private String titulo;

        @NotNull
        private Long idAutor;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Long getIdAutor() {
            return idAutor;
        }

        public void setIdAutor(Long idAutor) {
            this.idAutor = idAutor;
        }
    }
}
