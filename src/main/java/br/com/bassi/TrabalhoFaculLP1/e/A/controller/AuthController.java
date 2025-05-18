package br.com.bassi.TrabalhoFaculLP1.e.A.controller;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Funcionario;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.LoginRequest;
import br.com.bassi.TrabalhoFaculLP1.e.A.infra.JwtUtil;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.FuncionarioService;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.AuthService;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.FuncionarioDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.LoginRequest;
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

    private final FuncionarioService funcionarioService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/cadastro")
    public ResponseEntity<Funcionario> cadastrar(@RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = funcionarioService.cadastrarFuncionario(dto);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest dto, HttpServletResponse response) {
        String token = authService.autenticar(dto.email(), dto.senha());

        // Define o cookie com o token JWT
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true); // Protege contra scripts (recomendado)
        cookie.setPath("/"); // Envia o cookie em todas as rotas da aplicação
        cookie.setMaxAge(60 * 60); // 1 hora de validade

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expira imediatamente

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }



}

