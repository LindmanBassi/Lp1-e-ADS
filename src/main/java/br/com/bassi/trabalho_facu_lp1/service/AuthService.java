package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.infra.JwtUtil;
import br.com.bassi.trabalho_facu_lp1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public String autenticar(String email, String senha) {
        var usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            var usuario = usuarioOpt.get();
            if (!encoder.matches(senha, usuario.getSenha())) {
                throw new RuntimeException("Senha inválida");
            }
            return jwtUtil.gerarToken(usuario.getEmail());
        }
        throw new RuntimeException("Usuário não encontrado");
    }
}

