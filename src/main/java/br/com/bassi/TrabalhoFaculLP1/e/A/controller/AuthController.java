package br.com.bassi.TrabalhoFaculLP1.e.A.controller;

import br.com.bassi.TrabalhoFaculLP1.e.A.dto.LoginRequest;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.PessoaService;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.AuthService;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.PessoaDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PessoaService pessoaService;
    private final AuthService authService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody PessoaDTO dto) {
        pessoaService.cadastrarPessoa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest dto, HttpServletResponse response) {
        String token = authService.autenticar(dto.email(), dto.senha());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}





