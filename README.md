# Biblioteca API

API de biblioteca em português com tema de biblioteca.

## Funcionalidades

- Entidades relacionadas: `Autor` e `Livro`
- Autenticação com Spring Security usando JWT
- Documentação automática com Swagger/OpenAPI
- Banco de dados em memória H2 para testes

## Como rodar

```bash
mvn spring-boot:run
```

## URLs úteis

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

## Endpoints principais

- `POST /api/autenticacao/login` - autenticar usuário
- `GET /api/autores` - listar autores
- `POST /api/autores` - criar autor
- `GET /api/livros` - listar livros
- `POST /api/livros` - criar livro

## Usuários de exemplo

- `admin` / `admin123`
- `user` / `user123`

## Como autenticar

1. Faça login em `/api/autenticacao/login` com JSON:

```json
{
  "nomeUsuario": "admin",
  "senha": "admin123"
}
```

2. Use o token retornado no cabeçalho:

```
Authorization: Bearer <token>
```

## Notas

- O projeto deve ser usado para fins de estudo e entrega de atividade.
- As rotas de autores e livros exigem token JWT.
