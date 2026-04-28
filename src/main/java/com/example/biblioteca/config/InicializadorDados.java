package com.example.biblioteca.config;

import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.entity.Papel;
import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.PapelRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InicializadorDados implements CommandLineRunner {

    private final PapelRepository papelRepository;
    private final UsuarioRepository usuarioRepository;
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final PasswordEncoder passwordEncoder;

    public InicializadorDados(PapelRepository papelRepository,
                             UsuarioRepository usuarioRepository,
                             AutorRepository autorRepository,
                             LivroRepository livroRepository,
                             PasswordEncoder passwordEncoder) {
        this.papelRepository = papelRepository;
        this.usuarioRepository = usuarioRepository;
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Papel admin = papelRepository.findByNome("ROLE_ADMIN").orElseGet(() -> papelRepository.save(new Papel("ROLE_ADMIN")));
        Papel user = papelRepository.findByNome("ROLE_USER").orElseGet(() -> papelRepository.save(new Papel("ROLE_USER")));

        if (usuarioRepository.findByNomeUsuario("admin").isEmpty()) {
            Usuario usuarioAdmin = new Usuario("admin", passwordEncoder.encode("admin123"));
            usuarioAdmin.setPapeis(Set.of(admin));
            usuarioRepository.save(usuarioAdmin);
        }

        if (usuarioRepository.findByNomeUsuario("user").isEmpty()) {
            Usuario usuario = new Usuario("user", passwordEncoder.encode("user123"));
            usuario.setPapeis(Set.of(user));
            usuarioRepository.save(usuario);
        }

        if (autorRepository.count() == 0) {
            Autor autor1 = autorRepository.save(new Autor("Machado de Assis"));
            Autor autor2 = autorRepository.save(new Autor("Clarice Lispector"));
            livroRepository.save(new Livro("Dom Casmurro", autor1));
            livroRepository.save(new Livro("A Hora da Estrela", autor2));
        }
    }
}
