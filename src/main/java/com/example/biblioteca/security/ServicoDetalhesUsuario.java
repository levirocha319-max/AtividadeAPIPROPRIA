package com.example.biblioteca.security;

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoDetalhesUsuario implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public ServicoDetalhesUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + nomeUsuario));

        List<GrantedAuthority> autoridades = usuario.getPapeis().stream()
                .map(p -> new SimpleGrantedAuthority(p.getNome()))
                .collect(Collectors.toList());

        return User.builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenha())
                .authorities(autoridades)
                .build();
    }
}
