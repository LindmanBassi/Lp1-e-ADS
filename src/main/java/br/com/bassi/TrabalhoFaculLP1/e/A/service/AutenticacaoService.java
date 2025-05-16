package br.com.bassi.TrabalhoFaculLP1.e.A.service;

import br.com.bassi.TrabalhoFaculLP1.e.A.infra.JwtUtil;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil
}
