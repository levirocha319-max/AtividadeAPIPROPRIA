package com.example.biblioteca.controller;

import com.example.biblioteca.dto.RequisicaoLogin;
import com.example.biblioteca.dto.RespostaAutenticacao;
import com.example.biblioteca.security.ProvedorTokenJwt;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final ProvedorTokenJwt provedorTokenJwt;

    public AutenticacaoController(AuthenticationManager authenticationManager, ProvedorTokenJwt provedorTokenJwt) {
        this.authenticationManager = authenticationManager;
        this.provedorTokenJwt = provedorTokenJwt;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaAutenticacao> autenticar(@Valid @RequestBody RequisicaoLogin requisicaoLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requisicaoLogin.getNomeUsuario(), requisicaoLogin.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provedorTokenJwt.gerarToken(authentication);
        return ResponseEntity.ok(new RespostaAutenticacao(token));
    }
}
